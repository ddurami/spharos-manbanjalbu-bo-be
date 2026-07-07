package com.spharos.manbanjalbu_bo_be.domain.product.dto;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.Category;

public record CategoryResponse(
		Long categoryId,
		String name,
		int depth,
		Long parentId,
		Integer displayOrder
) {
	public static CategoryResponse from(Category category) {
		return new CategoryResponse(
				category.getId(),
				category.getName(),
				category.getDepth(),
				category.getParent() == null ? null : category.getParent().getId(),
				category.getDisplayOrder()
		);
	}
}
