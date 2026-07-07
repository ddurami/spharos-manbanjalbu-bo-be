package com.spharos.manbanjalbu_bo_be.domain.dashboard.dto;

import java.time.LocalDate;

public record WeeklySalesPoint(
		LocalDate date,
		long sales
) {
}
