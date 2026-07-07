package com.spharos.manbanjalbu_bo_be.domain.member.entity;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
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
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_admin_memo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAdminMemo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_member_id", nullable = false)
	private Admin admin;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Builder
	public MemberAdminMemo(Member member, Admin admin, String content) {
		this.member = member;
		this.admin = admin;
		this.content = content;
	}

	public void updateContent(String content) {
		this.content = content;
	}
}
