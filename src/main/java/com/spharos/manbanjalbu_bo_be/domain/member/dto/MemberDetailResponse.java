package com.spharos.manbanjalbu_bo_be.domain.member.dto;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;
import com.spharos.manbanjalbu_bo_be.domain.order.entity.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MemberDetailResponse(
		Long memberId,
		String loginId,
		String name,
		String nickname,
		MemberGrade grade,
		MemberStatus status,
		String email,
		String phone,
		LocalDate birthDate,
		boolean marketingEmailAgreed,
		boolean marketingSmsAgreed,
		long totalPurchaseAmount,
		LocalDateTime createdAt,
		LocalDateTime lastLoginAt,
		List<MemberAddressResponse> addresses,
		List<MemberOrderItem> recentOrders,
		List<MemberMemoResponse> memos
) {
	public record MemberAddressResponse(
			Long addressId,
			String addressName,
			String recipientName,
			String zipcode,
			String baseAddress,
			String detailAddress,
			String phone1,
			boolean isDefault
	) {
	}

	public record MemberOrderItem(
			Long orderId,
			String orderNo,
			String orderName,
			OrderStatus orderStatus,
			int orderAmount,
			LocalDateTime orderAt
	) {
	}

	public record MemberMemoResponse(
			Long memoId,
			String adminName,
			String content,
			LocalDateTime createdAt,
			LocalDateTime updatedAt
	) {
	}
}
