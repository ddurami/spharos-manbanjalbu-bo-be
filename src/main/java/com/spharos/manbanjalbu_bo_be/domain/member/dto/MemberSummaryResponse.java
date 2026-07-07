package com.spharos.manbanjalbu_bo_be.domain.member.dto;

public record MemberSummaryResponse(
		long totalCount,
		long newMembersToday,
		long newMembersThisMonth,
		long activeCount,
		long suspendedCount,
		long withdrawnCount
) {
}
