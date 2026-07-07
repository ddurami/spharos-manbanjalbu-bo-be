package com.spharos.manbanjalbu_bo_be.domain.member.controller;

import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberActionRequest;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberDetailResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberListItem;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberMemoRequest;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberSummaryResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;
import com.spharos.manbanjalbu_bo_be.domain.member.service.MemberManagementService;
import com.spharos.manbanjalbu_bo_be.global.common.ApiResponse;
import com.spharos.manbanjalbu_bo_be.global.security.AdminUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberManagementService memberManagementService;

	@GetMapping("/summary")
	public ResponseEntity<ApiResponse<MemberSummaryResponse>> getSummary() {
		return ResponseEntity.ok(ApiResponse.ok(memberManagementService.getSummary()));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Page<MemberListItem>>> searchMembers(
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) MemberGrade grade,
			@RequestParam(required = false) MemberStatus status,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registeredFrom,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registeredTo,
			@RequestParam(required = false) Long minAmount,
			@RequestParam(required = false) Long maxAmount,
			@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		LocalDateTime createdFrom = registeredFrom != null ? registeredFrom.atStartOfDay() : null;
		LocalDateTime createdTo = registeredTo != null ? registeredTo.plusDays(1).atStartOfDay() : null;

		Page<MemberListItem> result = memberManagementService.searchMembers(
				keyword, name, grade, status, createdFrom, createdTo, minAmount, maxAmount, pageable);
		return ResponseEntity.ok(ApiResponse.ok(result));
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<ApiResponse<MemberDetailResponse>> getMemberDetail(@PathVariable Long memberId) {
		return ResponseEntity.ok(ApiResponse.ok(memberManagementService.getMemberDetail(memberId)));
	}

	@PostMapping("/{memberId}/memos")
	public ResponseEntity<ApiResponse<MemberDetailResponse.MemberMemoResponse>> addMemo(
			@PathVariable Long memberId,
			@Valid @RequestBody MemberMemoRequest request,
			@AuthenticationPrincipal AdminUserDetails userDetails
	) {
		return ResponseEntity.ok(ApiResponse.ok(
				memberManagementService.addMemo(memberId, request, userDetails.getAdmin())));
	}

	@PostMapping("/{memberId}/suspend")
	public ResponseEntity<ApiResponse<Void>> suspendMember(
			@PathVariable Long memberId,
			@Valid @RequestBody MemberActionRequest request,
			@AuthenticationPrincipal AdminUserDetails userDetails,
			HttpServletRequest httpRequest
	) {
		memberManagementService.suspendMember(
				memberId, request, userDetails.getAdmin(), getClientIp(httpRequest));
		return ResponseEntity.ok(ApiResponse.ok(null));
	}

	@PostMapping("/{memberId}/withdraw")
	public ResponseEntity<ApiResponse<Void>> withdrawMember(
			@PathVariable Long memberId,
			@Valid @RequestBody MemberActionRequest request,
			@AuthenticationPrincipal AdminUserDetails userDetails,
			HttpServletRequest httpRequest
	) {
		memberManagementService.withdrawMember(
				memberId, request, userDetails.getAdmin(), getClientIp(httpRequest));
		return ResponseEntity.ok(ApiResponse.ok(null));
	}

	private String getClientIp(HttpServletRequest request) {
		String forwarded = request.getHeader("X-Forwarded-For");
		if (forwarded != null && !forwarded.isBlank()) {
			return forwarded.split(",")[0].trim();
		}
		return request.getRemoteAddr();
	}
}
