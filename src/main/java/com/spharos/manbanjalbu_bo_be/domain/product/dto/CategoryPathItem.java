package com.spharos.manbanjalbu_bo_be.domain.product.dto;

public record CategoryPathItem(
		Long categoryId,
		String name,
		int depth
) {
}
