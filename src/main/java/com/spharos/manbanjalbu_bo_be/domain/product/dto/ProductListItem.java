package com.spharos.manbanjalbu_bo_be.domain.product.dto;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductSaleType;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ProductListItem(
		Long productId,
		String name,
		String thumbnailUrl,
		String categoryName,
		int price,
		ProductStatus status,
		ProductSaleType saleType,
		List<String> badges,
		int salesCount,
		LocalDateTime createdAt
) {
}
