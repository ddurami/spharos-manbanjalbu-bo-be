package com.spharos.manbanjalbu_bo_be.domain.dashboard.service;

import com.spharos.manbanjalbu_bo_be.domain.dashboard.dto.DashboardResponse;
import com.spharos.manbanjalbu_bo_be.domain.dashboard.dto.DashboardShortcut;
import com.spharos.manbanjalbu_bo_be.domain.dashboard.dto.WeeklySalesPoint;
import com.spharos.manbanjalbu_bo_be.domain.member.repository.MemberRepository;
import com.spharos.manbanjalbu_bo_be.domain.order.entity.PaymentStatus;
import com.spharos.manbanjalbu_bo_be.domain.order.repository.OrderRepository;
import com.spharos.manbanjalbu_bo_be.domain.order.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardService {

	private static final int WEEKLY_TREND_DAYS = 7;

	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;

	public DashboardResponse getDashboard() {
		LocalDate today = LocalDate.now();
		LocalDateTime todayStart = today.atStartOfDay();
		LocalDateTime tomorrowStart = today.plusDays(1).atStartOfDay();
		LocalDateTime monthStart = today.withDayOfMonth(1).atStartOfDay();
		LocalDateTime nextMonthStart = today.withDayOfMonth(1).plusMonths(1).atStartOfDay();

		long todaySales = paymentRepository.sumAmountByStatusAndApprovedAtBetween(
				PaymentStatus.PAID, todayStart, tomorrowStart);
		long todayOrderCount = orderRepository.countByOrderAtBetween(todayStart, tomorrowStart);
		long newMembersToday = memberRepository.countByCreatedAtBetween(todayStart, tomorrowStart);
		long newMembersThisMonth = memberRepository.countByCreatedAtBetween(monthStart, nextMonthStart);

		return new DashboardResponse(
				todaySales,
				todayOrderCount,
				newMembersToday,
				newMembersThisMonth,
				buildWeeklySalesTrend(today),
				buildShortcuts()
		);
	}

	private List<WeeklySalesPoint> buildWeeklySalesTrend(LocalDate today) {
		LocalDate weekStart = today.minusDays(WEEKLY_TREND_DAYS - 1L);
		LocalDateTime start = weekStart.atStartOfDay();
		LocalDateTime end = today.plusDays(1).atStartOfDay();

		Map<LocalDate, Long> salesByDate = new HashMap<>();
		for (Object[] row : paymentRepository.findApprovedAmounts(PaymentStatus.PAID, start, end)) {
			LocalDateTime approvedAt = (LocalDateTime) row[0];
			long amount = ((Number) row[1]).longValue();
			salesByDate.merge(approvedAt.toLocalDate(), amount, Long::sum);
		}

		List<WeeklySalesPoint> trend = new ArrayList<>(WEEKLY_TREND_DAYS);
		for (int i = 0; i < WEEKLY_TREND_DAYS; i++) {
			LocalDate date = weekStart.plusDays(i);
			trend.add(new WeeklySalesPoint(date, salesByDate.getOrDefault(date, 0L)));
		}
		return trend;
	}

	private List<DashboardShortcut> buildShortcuts() {
		return List.of(
				new DashboardShortcut("MEMBER_SEARCH", "회원검색", "/members"),
				new DashboardShortcut("PRODUCT_CREATE", "상품등록", "/products/new"),
				new DashboardShortcut("NOTICE", "공지사항", "/notices"),
				new DashboardShortcut("SEARCH_KEYWORD", "검색어 등록", "/search-keywords")
		);
	}
}
