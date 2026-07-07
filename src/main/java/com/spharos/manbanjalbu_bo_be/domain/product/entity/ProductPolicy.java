package com.spharos.manbanjalbu_bo_be.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_policy")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(name = "delivery_info", nullable = false, columnDefinition = "TEXT")
	private String deliveryInfo;

	@Column(name = "exchange_info", nullable = false, columnDefinition = "TEXT")
	private String exchangeInfo;

	@Column(name = "refund_info", nullable = false, columnDefinition = "TEXT")
	private String refundInfo;
}
