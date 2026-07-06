package com.spharos.manbanjalbu_bo_be.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "login_id", nullable = false, unique = true, length = 50)
	private String loginId;

	@Column(nullable = false, length = 255)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private MemberGrade grade;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private MemberStatus status;

	@Column(name = "warning_count", nullable = false)
	private int warningCount;

	@Column(name = "last_login_at")
	private LocalDateTime lastLoginAt;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Builder
	public Member(
			String loginId,
			String password,
			MemberGrade grade,
			MemberStatus status,
			int warningCount
	) {
		this.loginId = loginId;
		this.password = password;
		this.grade = grade;
		this.status = status;
		this.warningCount = warningCount;
	}
}
