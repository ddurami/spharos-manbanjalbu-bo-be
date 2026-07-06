package com.spharos.manbanjalbu_bo_be.domain.admin.service;

import com.spharos.manbanjalbu_bo_be.domain.admin.dto.AdminLoginRequest;
import com.spharos.manbanjalbu_bo_be.domain.admin.dto.AdminLoginResponse;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminRepository;
import com.spharos.manbanjalbu_bo_be.global.security.AdminUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

	private final AuthenticationManager authenticationManager;
	private final AdminRepository adminRepository;

	@Transactional
	public AdminLoginResponse login(AdminLoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword())
		);

		AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
		Admin admin = adminRepository.findById(userDetails.getMember().getId())
				.orElseThrow();
		admin.recordLogin();

		return AdminLoginResponse.from(admin);
	}
}
