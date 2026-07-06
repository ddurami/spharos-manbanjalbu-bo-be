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
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "recommended_keyword")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendedKeyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String label;

	@Column(nullable = false, length = 100)
	private String keyword;

	@Column(name = "display_order", nullable = false)
	private int displayOrder;

	@Column(name = "is_active", nullable = false)
	private boolean active;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
}
