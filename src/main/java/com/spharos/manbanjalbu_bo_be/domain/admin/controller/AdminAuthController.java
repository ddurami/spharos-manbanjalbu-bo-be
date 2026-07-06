package com.spharos.manbanjalbu_bo_be.domain.admin.controller;

import com.spharos.manbanjalbu_bo_be.domain.admin.dto.AdminLoginRequest;
import com.spharos.manbanjalbu_bo_be.domain.admin.dto.AdminLoginResponse;
import com.spharos.manbanjalbu_bo_be.domain.admin.service.AdminAuthService;
import com.spharos.manbanjalbu_bo_be.global.common.ApiResponse;
import com.spharos.manbanjalbu_bo_be.global.security.AdminUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {

	private final AdminAuthService adminAuthService;

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AdminLoginResponse>> login(@Valid @RequestBody AdminLoginRequest request) {
		return ResponseEntity.ok(ApiResponse.ok(adminAuthService.login(request)));
	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<AdminLoginResponse>> me(@AuthenticationPrincipal AdminUserDetails userDetails) {
		return ResponseEntity.ok(ApiResponse.ok(adminAuthService.getCurrentAdmin(userDetails)));
	}
}
