package com.spharos.manbanjalbu_bo_be.domain.member.service;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminAuditLog;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminAuditLogRepository;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberAddressResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberDetailResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberListItem;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberMemoRequest;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberMemoResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberStatusChangeRequest;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberSummaryResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.RecentOrderResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberAdminMemo;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberProfile;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberAddressRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberAdminMemoRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberProfileRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberRepository;
import com.spharos.manbanjalbu_bo_be.domain.order.entity.PaymentStatus;
import com.spharos.manbanjalbu_bo_be.domain.order.repository.OrderRepository;
import com.spharos.manbanjalbu_bo_be.domain.order.repository.PaymentRepository;
import com.spharos.manbanjalbu_bo_be.global.exception.BusinessException;
import com.spharos.manbanjalbu_bo_be.global.exception.ErrorCode;
import com.spharos.manbanjalbu_bo_be.global.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberManagementService {

	private static final PaymentStatus PAID = PaymentStatus.PAID;

	private final MemberRepository memberRepository;
	private final MemberProfileRepository memberProfileRepository;
	private final MemberAddressRepository memberAddressRepository;
	private final MemberAdminMemoRepository memberAdminMemoRepository;
	private final OrderRepository orderRepository;
	private final PaymentRepository paymentRepository;
	private final AdminRepository adminRepository;
	private final AdminAuditLogRepository adminAuditLogRepository;
	private final MailService mailService;

	public MemberSummaryResponse getSummary() {
		LocalDate today = LocalDate.now();
		LocalDateTime todayStart = today.atStartOfDay();
		LocalDateTime tomorrowStart = today.plusDays(1).atStartOfDay();
		LocalDateTime monthStart = today.withDayOfMonth(1).atStartOfDay();
		LocalDateTime nextMonthStart = today.withDayOfMonth(1).plusMonths(1).atStartOfDay();

		return new MemberSummaryResponse(
				memberRepository.count(),
				memberRepository.countByCreatedAtBetween(todayStart, tomorrowStart),
				memberRepository.countByCreatedAtBetween(monthStart, nextMonthStart)
		);
	}

	public Page<MemberListItem> searchMembers(
			String name,
			MemberGrade grade,
			MemberStatus status,
			LocalDate joinedFrom,
			LocalDate joinedTo,
			Long minAmount,
			Long maxAmount,
			Pageable pageable
	) {
		String normalizedName = (name == null || name.isBlank()) ? null : name.trim();
		LocalDateTime joinedFromDateTime = (joinedFrom == null) ? null : joinedFrom.atStartOfDay();
		LocalDateTime joinedToDateTime = (joinedTo == null) ? null : joinedTo.plusDays(1).atStartOfDay();

		return memberRepository.searchMembers(
				normalizedName,
				grade,
				status,
				joinedFromDateTime,
				joinedToDateTime,
				minAmount,
				maxAmount,
				PAID,
				pageable
		);
	}

	public MemberDetailResponse getMemberDetail(Long memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

		MemberProfile profile = memberProfileRepository.findById(memberId).orElse(null);

		List<MemberAddressResponse> addresses = memberAddressRepository
				.findByMember_IdOrderByIsDefaultDescCreatedAtDesc(memberId)
				.stream()
				.map(MemberAddressResponse::from)
				.toList();

		List<RecentOrderResponse> recentOrders = orderRepository
				.findTop5ByMember_IdOrderByOrderAtDesc(memberId)
				.stream()
				.map(RecentOrderResponse::from)
				.toList();

		long totalPaymentAmount = paymentRepository.sumAmountByMemberIdAndStatus(memberId, PAID);

		String adminMemo = memberAdminMemoRepository.findById(memberId)
				.map(MemberAdminMemo::getContent)
				.orElse(null);

		return new MemberDetailResponse(
				member.getId(),
				member.getLoginId(),
				profile == null ? null : profile.getName(),
				profile == null ? null : profile.getNickname(),
				member.getGrade(),
				member.getStatus(),
				profile == null ? null : profile.getEmail(),
				profile == null ? null : profile.getPhone(),
				profile == null ? null : profile.getBirthDate(),
				member.getCreatedAt(),
				member.getLastLoginAt(),
				totalPaymentAmount,
				profile != null && profile.isMarketingEmailAgreed(),
				profile != null && profile.isMarketingSmsAgreed(),
				adminMemo,
				addresses,
				recentOrders
		);
	}

	@Transactional
	public MemberMemoResponse upsertMemo(Long memberId, MemberMemoRequest request, Long adminId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

		MemberAdminMemo memo = memberAdminMemoRepository.findById(memberId)
				.map(existing -> {
					existing.update(request.content(), adminId);
					return existing;
				})
				.orElseGet(() -> MemberAdminMemo.builder()
						.member(member)
						.content(request.content())
						.updatedByAdminId(adminId)
						.build());

		return MemberMemoResponse.from(memberAdminMemoRepository.save(memo));
	}

	@Transactional
	public void suspendMember(Long memberId, MemberStatusChangeRequest request, Long adminId, String ipAddress) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

		if (member.isWithdrawn()) {
			throw new BusinessException(ErrorCode.MEMBER_ALREADY_WITHDRAWN);
		}
		if (member.isSuspended()) {
			throw new BusinessException(ErrorCode.MEMBER_ALREADY_SUSPENDED);
		}

		member.suspend();
		recordAuditLog(adminId, "MEMBER_SUSPEND", memberId, request.reason(), ipAddress);
		sendStatusChangeMail(memberId, request);
	}

	@Transactional
	public void withdrawMember(Long memberId, MemberStatusChangeRequest request, Long adminId, String ipAddress) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

		if (member.isWithdrawn()) {
			throw new BusinessException(ErrorCode.MEMBER_ALREADY_WITHDRAWN);
		}

		member.withdraw();
		recordAuditLog(adminId, "MEMBER_WITHDRAW", memberId, request.reason(), ipAddress);
		sendStatusChangeMail(memberId, request);
	}

	private void sendStatusChangeMail(Long memberId, MemberStatusChangeRequest request) {
		String email = memberProfileRepository.findById(memberId)
				.map(MemberProfile::getEmail)
				.orElseThrow(() -> new BusinessException(ErrorCode.EMAIL_SEND_FAILED));

		mailService.send(email, request.emailSubject(), request.emailBody());
	}

	private void recordAuditLog(Long adminId, String actionType, Long targetId, String reason, String ipAddress) {
		Admin admin = adminRepository.getReferenceById(adminId);
		adminAuditLogRepository.save(AdminAuditLog.builder()
				.admin(admin)
				.actionType(actionType)
				.targetId(targetId)
				.description(truncate(reason))
				.ipAddress(ipAddress)
				.build());
	}

	private String truncate(String value) {
		if (value == null) {
			return "";
		}
		return value.length() > 255 ? value.substring(0, 255) : value;
	}
}
