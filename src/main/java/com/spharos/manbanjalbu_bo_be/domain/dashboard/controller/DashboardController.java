package com.spharos.manbanjalbu_bo_be.domain.dashboard.controller;

import com.spharos.manbanjalbu_bo_be.domain.dashboard.dto.DashboardResponse;
import com.spharos.manbanjalbu_bo_be.domain.dashboard.service.DashboardService;
import com.spharos.manbanjalbu_bo_be.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class DashboardController {

	private final DashboardService dashboardService;

	@GetMapping
	public ResponseEntity<ApiResponse<DashboardResponse>> getDashboard() {
		return ResponseEntity.ok(ApiResponse.ok(dashboardService.getDashboard()));
	}
}
