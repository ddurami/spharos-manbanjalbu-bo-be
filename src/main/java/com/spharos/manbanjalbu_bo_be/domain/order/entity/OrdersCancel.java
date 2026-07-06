package com.spharos.manbanjalbu_bo_be.domain.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders_cancel")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdersCancel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Orders order;

	@Enumerated(EnumType.STRING)
	@Column(name = "cancel_type", nullable = false, length = 20)
	private CancelType cancelType;

	@Enumerated(EnumType.STRING)
	@Column(name = "cancel_status", nullable = false, length = 20)
	private CancelStatus cancelStatus;

	@Column(name = "cancel_reason", nullable = false, length = 255)
	private String cancelReason;

	@Column(name = "cancel_by", nullable = false, length = 50)
	private String cancelBy;

	@Column(name = "cancelled_at", nullable = false)
	private LocalDateTime cancelledAt;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
}
