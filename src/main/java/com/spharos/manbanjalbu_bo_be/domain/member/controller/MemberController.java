package com.spharos.manbanjalbu_bo_be.domain.member.controller;

import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberDetailResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberListItem;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberMemoRequest;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberMemoResponse;
import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberStatusChangeRequest;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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
			@RequestParam(required = false) String name,
			@RequestParam(required = false) MemberGrade grade,
			@RequestParam(required = false) MemberStatus status,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate joinedFrom,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate joinedTo,
			@RequestParam(required = false) Long minAmount,
			@RequestParam(required = false) Long maxAmount,
			@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		Page<MemberListItem> result = memberManagementService.searchMembers(
				name, grade, status, joinedFrom, joinedTo, minAmount, maxAmount, pageable);
		return ResponseEntity.ok(ApiResponse.ok(result));
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<ApiResponse<MemberDetailResponse>> getMemberDetail(@PathVariable Long memberId) {
		return ResponseEntity.ok(ApiResponse.ok(memberManagementService.getMemberDetail(memberId)));
	}

	@PutMapping("/{memberId}/memo")
	public ResponseEntity<ApiResponse<MemberMemoResponse>> upsertMemo(
			@PathVariable Long memberId,
			@Valid @RequestBody MemberMemoRequest request,
			@AuthenticationPrincipal AdminUserDetails userDetails
	) {
		MemberMemoResponse response = memberManagementService.upsertMemo(
				memberId, request, userDetails.getAdmin().getMemberId());
		return ResponseEntity.ok(ApiResponse.ok(response));
	}

	@PostMapping("/{memberId}/suspend")
	public ResponseEntity<ApiResponse<Void>> suspendMember(
			@PathVariable Long memberId,
			@Valid @RequestBody MemberStatusChangeRequest request,
			@AuthenticationPrincipal AdminUserDetails userDetails,
			HttpServletRequest httpRequest
	) {
		memberManagementService.suspendMember(
				memberId, request, userDetails.getAdmin().getMemberId(), httpRequest.getRemoteAddr());
		return ResponseEntity.ok(ApiResponse.ok(null));
	}

	@PostMapping("/{memberId}/withdraw")
	public ResponseEntity<ApiResponse<Void>> withdrawMember(
			@PathVariable Long memberId,
			@Valid @RequestBody MemberStatusChangeRequest request,
			@AuthenticationPrincipal AdminUserDetails userDetails,
			HttpServletRequest httpRequest
	) {
		memberManagementService.withdrawMember(
				memberId, request, userDetails.getAdmin().getMemberId(), httpRequest.getRemoteAddr());
		return ResponseEntity.ok(ApiResponse.ok(null));
	}
}
