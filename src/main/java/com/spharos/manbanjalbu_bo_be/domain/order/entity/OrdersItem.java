package com.spharos.manbanjalbu_bo_be.domain.order.entity;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Table(name = "orders_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdersItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Orders order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "product_name", nullable = false, length = 200)
	private String productName;

	@Column(name = "order_price", nullable = false)
	private int orderPrice;

	@Column(name = "discount_price", nullable = false)
	private int discountPrice;

	@Column(name = "final_price", nullable = false)
	private int finalPrice;

	@Column(nullable = false)
	private int quantity;
}
