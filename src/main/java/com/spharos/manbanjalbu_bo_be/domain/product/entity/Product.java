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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "policy_id", nullable = false)
	private ProductPolicy policy;

	@Column(nullable = false, length = 200)
	private String name;

	@Column(name = "short_description", length = 255)
	private String shortDescription;

	@Column(nullable = false)
	private int price;

	@Enumerated(EnumType.STRING)
	@Column(name = "sale_type", nullable = false, length = 20)
	private ProductSaleType saleType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "season_id")
	private Season season;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ProductCapacity capacity;

	@Column(name = "is_best", nullable = false)
	private boolean best;

	@Column(name = "is_new", nullable = false)
	private boolean isNew;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private ProductStatus status;

	@Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
	private boolean deleted;

	@Column(name = "status_change_reason", columnDefinition = "TEXT")
	private String statusChangeReason;

	@Column(name = "status_changed_by_admin_id")
	private Long statusChangedByAdminId;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Builder
	public Product(
			Category category,
			ProductPolicy policy,
			String name,
			String shortDescription,
			int price,
			ProductSaleType saleType,
			Season season,
			ProductCapacity capacity,
			boolean best,
			boolean isNew,
			ProductStatus status
	) {
		this.category = category;
		this.policy = policy;
		this.name = name;
		this.shortDescription = shortDescription;
		this.price = price;
		this.saleType = saleType;
		this.season = season;
		this.capacity = capacity;
		this.best = best;
		this.isNew = isNew;
		this.status = status;
		this.deleted = false;
	}

	public void markSoldOut(String reason, Long adminId) {
		this.status = ProductStatus.SOLD_OUT;
		this.statusChangeReason = reason;
		this.statusChangedByAdminId = adminId;
	}

	public void stopSelling(String reason, Long adminId) {
		this.status = ProductStatus.HIDDEN;
		this.statusChangeReason = reason;
		this.statusChangedByAdminId = adminId;
	}

	public void softDelete(String reason, Long adminId) {
		this.deleted = true;
		this.statusChangeReason = reason;
		this.statusChangedByAdminId = adminId;
	}

	public void updateInfo(
			Category category,
			ProductPolicy policy,
			String name,
			String shortDescription,
			int price,
			ProductSaleType saleType,
			Season season,
			ProductCapacity capacity,
			boolean best,
			boolean isNew,
			ProductStatus status
	) {
		this.category = category;
		this.policy = policy;
		this.name = name;
		this.shortDescription = shortDescription;
		this.price = price;
		this.saleType = saleType;
		this.season = season;
		this.capacity = capacity;
		this.best = best;
		this.isNew = isNew;
		this.status = status;
	}
}
