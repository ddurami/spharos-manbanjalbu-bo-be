package com.spharos.manbanjalbu_bo_be.domain.product.dto;

public record ProductSummaryResponse(
		long totalCount,
		long onSaleCount,
		long soldOutCount,
		long hiddenCount,
		long deletedCount
) {
}
