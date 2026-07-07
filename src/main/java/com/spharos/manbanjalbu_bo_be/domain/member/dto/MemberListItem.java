package com.spharos.manbanjalbu_bo_be.domain.member.dto;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;

import java.time.LocalDateTime;

public record MemberListItem(
		Long memberId,
		String name,
		String loginId,
		MemberGrade grade,
		MemberStatus status,
		String email,
		String phone,
		long totalPurchaseAmount,
		LocalDateTime createdAt
) {
}
