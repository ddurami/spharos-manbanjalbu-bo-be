package com.spharos.manbanjalbu_bo_be.domain.admin.service;

import com.spharos.manbanjalbu_bo_be.config.JwtTokenProvider;
import com.spharos.manbanjalbu_bo_be.domain.admin.dto.AdminLoginRequest;
import com.spharos.manbanjalbu_bo_be.domain.admin.dto.AdminLoginResponse;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminStatus;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberProfile;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberProfileRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberRepository;
import com.spharos.manbanjalbu_bo_be.global.exception.BusinessException;
import com.spharos.manbanjalbu_bo_be.global.exception.ErrorCode;
import com.spharos.manbanjalbu_bo_be.global.security.AdminUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

	private final MemberRepository memberRepository;
	private final MemberProfileRepository memberProfileRepository;
	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;

	@Transactional
	public AdminLoginResponse login(AdminLoginRequest request) {
		Member member = memberRepository.findByLoginId(request.getLoginId())
				.orElseThrow(() -> new BusinessException(ErrorCode.INVALID_CREDENTIALS));

		Admin admin = adminRepository.findWithDetailsByMemberId(member.getId())
				.orElseThrow(() -> new BusinessException(ErrorCode.INVALID_CREDENTIALS));

		validateAdminLogin(member, admin, request.getPassword());

		admin.recordLogin();

		String accessToken = jwtTokenProvider.createAdminToken(
				member.getId(),
				member.getLoginId(),
				admin.getRole().getRoleName()
		);

		return AdminLoginResponse.from(admin, accessToken, resolveAdminName(member.getId(), member.getLoginId()));
	}

	@Transactional(readOnly = true)
	public AdminLoginResponse getCurrentAdmin(AdminUserDetails userDetails) {
		Admin admin = adminRepository.findWithDetailsByMemberId(userDetails.getMember().getId())
				.orElseThrow(() -> new BusinessException(ErrorCode.UNAUTHORIZED));

		return AdminLoginResponse.from(
				admin,
				resolveAdminName(admin.getMemberId(), admin.getMember().getLoginId())
		);
	}

	private void validateAdminLogin(Member member, Admin admin, String rawPassword) {
		if (member.getStatus() != MemberStatus.ACTIVE) {
			throw new BusinessException(ErrorCode.MEMBER_INACTIVE);
		}
		if (admin.getAdminStatus() != AdminStatus.ACTIVE) {
			throw new BusinessException(ErrorCode.ADMIN_INACTIVE);
		}
		if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
			throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
		}
	}

	private String resolveAdminName(Long memberId, String loginId) {
		return memberProfileRepository.findById(memberId)
				.map(MemberProfile::getName)
				.orElse(loginId);
	}
}
