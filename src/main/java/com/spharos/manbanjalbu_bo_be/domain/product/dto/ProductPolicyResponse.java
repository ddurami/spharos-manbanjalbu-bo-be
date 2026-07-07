package com.spharos.manbanjalbu_bo_be.domain.product.dto;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductPolicy;

public record ProductPolicyResponse(
		Long policyId,
		String title
) {
	public static ProductPolicyResponse from(ProductPolicy policy) {
		return new ProductPolicyResponse(policy.getId(), policy.getTitle());
	}
}
