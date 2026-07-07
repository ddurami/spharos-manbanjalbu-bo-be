package com.spharos.manbanjalbu_bo_be.domain.member.dto;

public record MemberSummaryResponse(
		long totalMembers,
		long newMembersToday,
		long newMembersThisMonth
) {
}
