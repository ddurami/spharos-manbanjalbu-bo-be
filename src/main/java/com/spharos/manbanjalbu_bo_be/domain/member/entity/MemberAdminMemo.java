package com.spharos.manbanjalbu_bo_be.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_admin_memo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAdminMemo {

	@Id
	@Column(name = "member_id")
	private Long memberId;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@Column(name = "updated_by_admin_id")
	private Long updatedByAdminId;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Builder
	public MemberAdminMemo(Member member, String content, Long updatedByAdminId) {
		this.member = member;
		this.content = content;
		this.updatedByAdminId = updatedByAdminId;
	}

	public void update(String content, Long updatedByAdminId) {
		this.content = content;
		this.updatedByAdminId = updatedByAdminId;
	}
}
