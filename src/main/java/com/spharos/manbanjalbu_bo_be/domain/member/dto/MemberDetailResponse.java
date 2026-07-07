package com.spharos.manbanjalbu_bo_be.domain.member.dto;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MemberDetailResponse(
		Long memberId,
		String loginId,
		String name,
		String nickname,
		MemberGrade grade,
		MemberStatus status,
		String email,
		String phone,
		LocalDate birthDate,
		LocalDateTime joinedAt,
		LocalDateTime lastLoginAt,
		long totalPaymentAmount,
		boolean marketingEmailAgreed,
		boolean marketingSmsAgreed,
		String adminMemo,
		List<MemberAddressResponse> addresses,
		List<RecentOrderResponse> recentOrders
) {
}
