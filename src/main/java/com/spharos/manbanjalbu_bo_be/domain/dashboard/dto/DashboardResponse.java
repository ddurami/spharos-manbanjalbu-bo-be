package com.spharos.manbanjalbu_bo_be.domain.dashboard.dto;

import java.util.List;

public record DashboardResponse(
		long todaySales,
		long todayOrderCount,
		long newMembersToday,
		long newMembersThisMonth,
		List<WeeklySalesPoint> weeklySalesTrend,
		List<DashboardShortcut> shortcuts
) {
}
