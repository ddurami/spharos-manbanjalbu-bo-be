package com.spharos.manbanjalbu_bo_be.domain.admin.controller;

import com.spharos.manbanjalbu_bo_be.domain.admin.dto.AdminLoginRequest;
import com.spharos.manbanjalbu_bo_be.domain.admin.dto.AdminLoginResponse;
import com.spharos.manbanjalbu_bo_be.domain.admin.service.AdminAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<AdminLoginResponse> login(@Valid @RequestBody AdminLoginRequest request) {
		return ResponseEntity.ok(adminAuthService.login(request));
	}
}
