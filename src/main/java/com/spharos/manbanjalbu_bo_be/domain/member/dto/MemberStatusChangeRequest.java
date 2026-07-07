package com.spharos.manbanjalbu_bo_be.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberStatusChangeRequest(
		@NotBlank(message = "사유를 입력해주세요.")
		@Size(max = 1000, message = "사유는 1000자 이하로 입력해주세요.")
		String reason,

		@NotBlank(message = "메일 제목을 입력해주세요.")
		@Size(max = 200, message = "메일 제목은 200자 이하로 입력해주세요.")
		String emailSubject,

		@NotBlank(message = "메일 내용을 입력해주세요.")
		String emailBody
) {
}
