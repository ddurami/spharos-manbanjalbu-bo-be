package com.spharos.manbanjalbu_bo_be.domain.member.service;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminAuditLog;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminAuditLogRepository;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberActionRequest;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberDetailResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberListItem;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberMemoRequest;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberSummaryResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberAdminMemo;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberAddress;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberProfile;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberAddressRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberAdminMemoRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberProfileRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberRepository;
import com.spharos.manbanjalbu_bo_be.domain.order.entity.Orders;
import com.spharos.manbanjalbu_bo_be.domain.order.repository.OrderRepository;
import com.spharos.manbanjalbu_bo_be.global.exception.BusinessException;
import com.spharos.manbanjalbu_bo_be.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberManagementService {

	private final MemberRepository memberRepository;
	private final MemberProfileRepository memberProfileRepository;
	private final MemberAddressRepository memberAddressRepository;
	private final MemberAdminMemoRepository memberAdminMemoRepository;
	private final OrderRepository orderRepository;
	private final AdminRepository adminRepository;
	private final AdminAuditLogRepository adminAuditLogRepository;
	private final MemberNotificationService memberNotificationService;

	@Transactional(readOnly = true)
	public MemberSummaryResponse getSummary() {
		LocalDate today = LocalDate.now();
		LocalDateTime todayStart = today.atStartOfDay();
		LocalDateTime tomorrowStart = today.plusDays(1).atStartOfDay();
		LocalDateTime monthStart = today.withDayOfMonth(1).atStartOfDay();
		LocalDateTime nextMonthStart = today.withDayOfMonth(1).plusMonths(1).atStartOfDay();

		return new MemberSummaryResponse(
				memberRepository.countCustomerMembers(),
				memberRepository.countByCreatedAtBetween(todayStart, tomorrowStart),
				memberRepository.countByCreatedAtBetween(monthStart, nextMonthStart),
				memberRepository.countByStatusExcludingAdmin(MemberStatus.ACTIVE),
				memberRepository.countByStatusExcludingAdmin(MemberStatus.SUSPENDED),
				memberRepository.countByStatusExcludingAdmin(MemberStatus.WITHDRAWN)
		);
	}

	@Transactional(readOnly = true)
	public Page<MemberListItem> searchMembers(
			String keyword,
			String name,
			MemberGrade grade,
			MemberStatus status,
			LocalDateTime createdFrom,
			LocalDateTime createdTo,
			Long minAmount,
			Long maxAmount,
			Pageable pageable
	) {
		Page<Member> page = memberRepository.searchMembers(
				emptyToNull(keyword),
				emptyToNull(name),
				grade,
				status,
				createdFrom,
				createdTo,
				minAmount,
				maxAmount,
				pageable
		);

		return page.map(member -> {
			MemberProfile profile = getProfile(member.getId());
			long totalAmount = orderRepository.sumOrderAmountByMemberId(member.getId());
			return new MemberListItem(
					member.getId(),
					profile.getName(),
					member.getLoginId(),
					member.getGrade(),
					member.getStatus(),
					profile.getEmail(),
					profile.getPhone(),
					totalAmount,
					member.getCreatedAt()
			);
		});
	}

	@Transactional(readOnly = true)
	public MemberDetailResponse getMemberDetail(Long memberId) {
		Member member = getCustomerMember(memberId);
		MemberProfile profile = getProfile(memberId);
		long totalAmount = orderRepository.sumOrderAmountByMemberId(memberId);

		List<MemberDetailResponse.MemberAddressResponse> addresses = memberAddressRepository
				.findByMember_IdOrderByIsDefaultDescCreatedAtDesc(memberId)
				.stream()
				.map(this::toAddressResponse)
				.toList();

		List<MemberDetailResponse.MemberOrderItem> recentOrders = orderRepository
				.findByMember_IdOrderByOrderAtDesc(memberId, PageRequest.of(0, 5))
				.stream()
				.map(this::toOrderItem)
				.toList();

		List<MemberDetailResponse.MemberMemoResponse> memos = memberAdminMemoRepository
				.findByMember_IdOrderByCreatedAtDesc(memberId)
				.stream()
				.map(this::toMemoResponse)
				.toList();

		return new MemberDetailResponse(
				member.getId(),
				member.getLoginId(),
				profile.getName(),
				profile.getNickname(),
				member.getGrade(),
				member.getStatus(),
				profile.getEmail(),
				profile.getPhone(),
				profile.getBirthDate(),
				profile.isMarketingEmailAgreed(),
				profile.isMarketingSmsAgreed(),
				totalAmount,
				member.getCreatedAt(),
				member.getLastLoginAt(),
				addresses,
				recentOrders,
				memos
		);
	}

	@Transactional
	public MemberDetailResponse.MemberMemoResponse addMemo(
			Long memberId,
			MemberMemoRequest request,
			Admin admin
	) {
		Member member = getCustomerMember(memberId);
		MemberAdminMemo memo = memberAdminMemoRepository.save(MemberAdminMemo.builder()
				.member(member)
				.admin(admin)
				.content(request.content())
				.build());
		return toMemoResponse(memo);
	}

	@Transactional
	public void suspendMember(Long memberId, MemberActionRequest request, Admin admin, String ip) {
		Member member = getCustomerMember(memberId);
		if (member.getStatus() == MemberStatus.SUSPENDED) {
			throw new BusinessException(ErrorCode.MEMBER_ALREADY_SUSPENDED);
		}
		if (member.getStatus() == MemberStatus.WITHDRAWN) {
			throw new BusinessException(ErrorCode.MEMBER_ALREADY_WITHDRAWN);
		}

		member.suspend();
		MemberProfile profile = getProfile(memberId);
		memberNotificationService.sendMemberEmail(
				profile.getEmail(), request.emailSubject(), request.emailBody());
		recordAuditLog(admin, "MEMBER_SUSPEND", memberId,
				"회원 정지: " + request.reason(), ip);
	}

	@Transactional
	public void withdrawMember(Long memberId, MemberActionRequest request, Admin admin, String ip) {
		Member member = getCustomerMember(memberId);
		if (member.getStatus() == MemberStatus.WITHDRAWN) {
			throw new BusinessException(ErrorCode.MEMBER_ALREADY_WITHDRAWN);
		}

		member.withdraw();
		MemberProfile profile = getProfile(memberId);
		memberNotificationService.sendMemberEmail(
				profile.getEmail(), request.emailSubject(), request.emailBody());
		recordAuditLog(admin, "MEMBER_WITHDRAW", memberId,
				"회원 탈퇴: " + request.reason(), ip);
	}

	private Member getCustomerMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
		if (adminRepository.existsById(memberId)) {
			throw new BusinessException(ErrorCode.MEMBER_IS_ADMIN);
		}
		return member;
	}

	private MemberProfile getProfile(Long memberId) {
		return memberProfileRepository.findById(memberId)
				.orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
	}

	private MemberDetailResponse.MemberAddressResponse toAddressResponse(MemberAddress address) {
		return new MemberDetailResponse.MemberAddressResponse(
				address.getId(),
				address.getAddressName(),
				address.getRecipientName(),
				address.getZipcode(),
				address.getBaseAddress(),
				address.getDetailAddress(),
				address.getPhone1(),
				address.isDefault()
		);
	}

	private MemberDetailResponse.MemberOrderItem toOrderItem(Orders order) {
		return new MemberDetailResponse.MemberOrderItem(
				order.getId(),
				order.getOrderNo(),
				order.getOrderName(),
				order.getOrderStatus(),
				order.getOrderAmount(),
				order.getOrderAt()
		);
	}

	private MemberDetailResponse.MemberMemoResponse toMemoResponse(MemberAdminMemo memo) {
		String adminName = memberProfileRepository.findById(memo.getAdmin().getMemberId())
				.map(MemberProfile::getName)
				.orElse("관리자");
		return new MemberDetailResponse.MemberMemoResponse(
				memo.getId(),
				adminName,
				memo.getContent(),
				memo.getCreatedAt(),
				memo.getUpdatedAt()
		);
	}

	private void recordAuditLog(Admin admin, String actionType, Long targetId, String description, String ip) {
		AdminAuditLog log = AdminAuditLog.builder()
				.admin(adminRepository.getReferenceById(admin.getMemberId()))
				.actionType(actionType)
				.targetId(targetId)
				.description(truncate(description, 255))
				.ipAddress(ip)
				.build();
		adminAuditLogRepository.save(log);
	}

	private String truncate(String value, int max) {
		if (value == null) {
			return null;
		}
		return value.length() <= max ? value : value.substring(0, max);
	}

	private String emptyToNull(String value) {
		return (value == null || value.isBlank()) ? null : value.trim();
	}
}
