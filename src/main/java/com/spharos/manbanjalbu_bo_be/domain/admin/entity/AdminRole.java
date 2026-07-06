package com.spharos.manbanjalbu_bo_be.domain.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_role")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_name", nullable = false, unique = true, length = 50)
	private String roleName;

	@Column(length = 255)
	private String description;

	@Column(name = "can_manage_product", nullable = false)
	private boolean canManageProduct;

	@Column(name = "can_manage_member", nullable = false)
	private boolean canManageMember;

	@Column(name = "can_manage_order", nullable = false)
	private boolean canManageOrder;

	@Column(name = "can_manage_system", nullable = false)
	private boolean canManageSystem;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Builder
	public AdminRole(
			String roleName,
			String description,
			boolean canManageProduct,
			boolean canManageMember,
			boolean canManageOrder,
			boolean canManageSystem
	) {
		this.roleName = roleName;
		this.description = description;
		this.canManageProduct = canManageProduct;
		this.canManageMember = canManageMember;
		this.canManageOrder = canManageOrder;
		this.canManageSystem = canManageSystem;
	}
}
