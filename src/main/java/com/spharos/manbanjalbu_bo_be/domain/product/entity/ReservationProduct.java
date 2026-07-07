package com.spharos.manbanjalbu_bo_be.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, unique = true)
	private Product product;

	@Column(name = "reservation_start_at", nullable = false)
	private LocalDateTime reservationStartAt;

	@Column(name = "reservation_end_at", nullable = false)
	private LocalDateTime reservationEndAt;

	@Column(name = "expected_release_at", nullable = false)
	private LocalDateTime expectedReleaseAt;

	@Column(name = "reservation_quantity", nullable = false)
	private int reservationQuantity;

	@Column(name = "reserved_quantity", nullable = false)
	private int reservedQuantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "reservation_status", nullable = false, length = 20)
	private ReservationStatus reservationStatus;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}
