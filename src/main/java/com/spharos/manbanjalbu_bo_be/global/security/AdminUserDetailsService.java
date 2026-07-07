package com.spharos.manbanjalbu_bo_be.global.security;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminStatus;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminRepository;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final AdminRepository adminRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String loginId) {
		Member member = memberRepository.findByLoginId(loginId)
				.orElseThrow(() -> new UsernameNotFoundException("관리자 계정을 찾을 수 없습니다: " + loginId));

		Admin admin = adminRepository.findWithDetailsByMemberId(member.getId())
				.orElseThrow(() -> new UsernameNotFoundException("관리자 권한이 없습니다: " + loginId));

		if (admin.getAdminStatus() != AdminStatus.ACTIVE) {
			throw new DisabledException("비활성화된 관리자 계정입니다.");
		}

		return new AdminUserDetails(member, admin);
	}
}
