package com.spharos.manbanjalbu_bo_be.domain.product.dto;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductCapacity;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductSaleType;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ProductDetailResponse(
		Long productId,
		String name,
		String shortDescription,
		int price,
		ProductStatus status,
		boolean deleted,
		ProductSaleType saleType,
		boolean best,
		boolean isNew,
		List<String> badges,
		ProductCapacity capacity,
		Long seasonId,
		String seasonName,
		Long categoryId,
		List<CategoryPathItem> categoryPath,
		Long policyId,
		String policyTitle,
		int salesCount,
		String thumbnailUrl,
		List<ProductMediaResponse> detailImages,
		String detailHtml,
		String statusChangeReason,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
) {
}
