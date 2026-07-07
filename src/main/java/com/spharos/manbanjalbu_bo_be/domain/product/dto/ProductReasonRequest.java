package com.spharos.manbanjalbu_bo_be.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductReasonRequest(
		@NotBlank(message = "사유를 입력해주세요.")
		@Size(max = 1000, message = "사유는 1000자 이하로 입력해주세요.")
		String reason
) {
}
