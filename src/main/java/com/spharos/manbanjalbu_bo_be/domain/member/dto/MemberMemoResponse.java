package com.spharos.manbanjalbu_bo_be.domain.member.dto;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberAdminMemo;

import java.time.LocalDateTime;

public record MemberMemoResponse(
		Long memberId,
		String content,
		Long updatedByAdminId,
		LocalDateTime updatedAt
) {
	public static MemberMemoResponse from(MemberAdminMemo memo) {
		return new MemberMemoResponse(
				memo.getMemberId(),
				memo.getContent(),
				memo.getUpdatedByAdminId(),
				memo.getUpdatedAt()
		);
	}
}
