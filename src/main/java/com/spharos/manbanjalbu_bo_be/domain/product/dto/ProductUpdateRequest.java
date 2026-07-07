package com.spharos.manbanjalbu_bo_be.domain.product.dto;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductCapacity;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductSaleType;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProductUpdateRequest(
		@NotNull(message = "카테고리를 선택해주세요.")
		Long categoryId,

		@NotNull(message = "상품 정책을 선택해주세요.")
		Long policyId,

		@NotBlank(message = "상품명을 입력해주세요.")
		@Size(max = 200, message = "상품명은 200자 이하로 입력해주세요.")
		String name,

		@NotBlank(message = "상품 간단 설명을 입력해주세요.")
		@Size(max = 255, message = "간단 설명은 255자 이하로 입력해주세요.")
		String shortDescription,

		@NotNull(message = "가격을 입력해주세요.")
		@Min(value = 0, message = "가격은 0 이상이어야 합니다.")
		Integer price,

		@NotNull(message = "판매 유형을 선택해주세요.")
		ProductSaleType saleType,

		@NotBlank(message = "상품 썸네일 URL을 입력해주세요.")
		@Size(max = 500, message = "썸네일 URL은 500자 이하로 입력해주세요.")
		String thumbnailUrl,

		Long seasonId,

		ProductCapacity capacity,

		ProductStatus status,

		boolean best,

		boolean isNew,

		List<String> detailImageUrls,

		String detailHtml
) {
}
