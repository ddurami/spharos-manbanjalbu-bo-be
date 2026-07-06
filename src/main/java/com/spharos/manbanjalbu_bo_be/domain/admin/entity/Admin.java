package com.spharos.manbanjalbu_bo_be.domain.admin.entity;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {

	@Id
	@Column(name = "member_id")
	private Long memberId;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private AdminRole role;

	@Enumerated(EnumType.STRING)
	@Column(name = "admin_status", nullable = false, columnDefinition = "ENUM('ACTIVE','SUSPENDED','RESIGNED')")
	private AdminStatus adminStatus;

	@Column(name = "last_admin_login_at")
	private LocalDateTime lastAdminLoginAt;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Builder
	public Admin(Member member, AdminRole role, AdminStatus adminStatus) {
		this.member = member;
		this.memberId = member.getId();
		this.role = role;
		this.adminStatus = adminStatus;
	}

	public void recordLogin() {
		this.lastAdminLoginAt = LocalDateTime.now();
	}
}
