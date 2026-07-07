package com.spharos.manbanjalbu_bo_be.domain.product.dto;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductMedia;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductMediaType;

public record ProductMediaResponse(
		Long mediaId,
		ProductMediaType mediaType,
		String mediaUrl,
		Integer displayOrder,
		boolean main
) {
	public static ProductMediaResponse from(ProductMedia media) {
		return new ProductMediaResponse(
				media.getId(),
				media.getMediaType(),
				media.getMediaUrl(),
				media.getDisplayOrder(),
				media.isMain()
		);
	}
}
