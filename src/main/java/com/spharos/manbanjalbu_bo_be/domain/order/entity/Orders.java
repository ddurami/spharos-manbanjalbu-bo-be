package com.spharos.manbanjalbu_bo_be.domain.order.entity;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberAddress;
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
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_address_id")
	private MemberAddress memberAddress;

	@Column(name = "order_no", nullable = false, unique = true, length = 50)
	private String orderNo;

	@Column(name = "order_name", nullable = false, length = 200)
	private String orderName;

	@Enumerated(EnumType.STRING)
	@Column(name = "order_status", nullable = false, length = 20)
	private OrderStatus orderStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "order_type", nullable = false, length = 20)
	private OrderType orderType;

	@Enumerated(EnumType.STRING)
	@Column(name = "order_category", length = 20)
	private OrderCategory orderCategory;

	@Column(name = "delivery_memo", length = 255)
	private String deliveryMemo;

	@Column(nullable = false)
	private int amount;

	@Column(name = "delivery_fee", nullable = false)
	private int deliveryFee;

	@Column(name = "order_amount", nullable = false)
	private int orderAmount;

	@Column(name = "reservation_delivery_date")
	private LocalDate reservationDeliveryDate;

	@Column(name = "recipient_name", nullable = false, length = 50)
	private String recipientName;

	@Column(name = "recipient_phone", nullable = false, length = 20)
	private String recipientPhone;

	@Column(name = "recipient_zipcode", nullable = false, length = 10)
	private String recipientZipcode;

	@Column(name = "recipient_base_address", nullable = false, length = 255)
	private String recipientBaseAddress;

	@Column(name = "recipient_detail_address", nullable = false, length = 255)
	private String recipientDetailAddress;

	@Column(name = "order_at", nullable = false)
	private LocalDateTime orderAt;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}
