package com.spharos.manbanjalbu_bo_be.domain.member.dto;

import com.spharos.manbanjalbu_bo_be.domain.order.entity.OrderStatus;
import com.spharos.manbanjalbu_bo_be.domain.order.entity.Orders;

import java.time.LocalDateTime;

public record RecentOrderResponse(
		Long orderId,
		String orderNo,
		String orderName,
		OrderStatus orderStatus,
		int orderAmount,
		LocalDateTime orderAt
) {
	public static RecentOrderResponse from(Orders order) {
		return new RecentOrderResponse(
				order.getId(),
				order.getOrderNo(),
				order.getOrderName(),
				order.getOrderStatus(),
				order.getOrderAmount(),
				order.getOrderAt()
		);
	}
}
