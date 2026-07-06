package com.spharos.manbanjalbu_bo_be.domain.member.entity;

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

import java.time.LocalDateTime;

@Entity
@Table(name = "member_auth")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAuth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Enumerated(EnumType.STRING)
	@Column(name = "auth_method", nullable = false, length = 20)
	private AuthMethod authMethod;

	@Column(name = "verified_value", nullable = false, length = 255)
	private String verifiedValue;

	@Column(length = 88)
	private String ci;

	@Column(name = "is_authenticated", nullable = false)
	private boolean authenticated;

	@Column(name = "authenticated_at", nullable = false)
	private LocalDateTime authenticatedAt;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
}
