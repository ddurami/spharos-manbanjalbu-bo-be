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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_profile")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfile {

	@Id
	@Column(name = "member_id")
	private Long memberId;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(unique = true, length = 50)
	private String nickname;

	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@Column(nullable = false, unique = true, length = 20)
	private String phone;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	@Column(name = "marketing_email_agreed", nullable = false)
	private boolean marketingEmailAgreed;

	@Column(name = "marketing_sms_agreed", nullable = false)
	private boolean marketingSmsAgreed;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Builder
	public MemberProfile(
			Member member,
			String name,
			String nickname,
			String email,
			String phone,
			LocalDate birthDate,
			boolean marketingEmailAgreed,
			boolean marketingSmsAgreed
	) {
		this.member = member;
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.marketingEmailAgreed = marketingEmailAgreed;
		this.marketingSmsAgreed = marketingSmsAgreed;
	}
}
