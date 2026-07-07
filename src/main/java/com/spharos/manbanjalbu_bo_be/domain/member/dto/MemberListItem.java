package com.spharos.manbanjalbu_bo_be.domain.member.dto;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;

import java.time.LocalDateTime;

public record MemberListItem(
		Long memberId,
		String loginId,
		String name,
		MemberGrade grade,
		MemberStatus status,
		LocalDateTime joinedAt,
		Long totalPaymentAmount
) {
}
