package com.spharos.manbanjalbu_bo_be.domain.admin.entity;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_audit_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminAuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_member_id", nullable = false)
	private Admin admin;

	@Column(name = "action_type", nullable = false, length = 50)
	private String actionType;

	@Column(name = "target_id")
	private Long targetId;

	@Column(nullable = false, length = 255)
	private String description;

	@Column(name = "ip_address", length = 50)
	private String ipAddress;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Builder
	public AdminAuditLog(Admin admin, String actionType, Long targetId, String description, String ipAddress) {
		this.admin = admin;
		this.actionType = actionType;
		this.targetId = targetId;
		this.description = description;
		this.ipAddress = ipAddress;
	}
}
