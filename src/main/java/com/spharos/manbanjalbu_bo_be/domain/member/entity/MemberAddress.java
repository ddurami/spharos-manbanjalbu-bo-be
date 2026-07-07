package com.spharos.manbanjalbu_bo_be.domain.member.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_address")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Column(name = "address_name", length = 50)
	private String addressName;

	@Column(name = "recipient_name", nullable = false, length = 50)
	private String recipientName;

	@Column(nullable = false, length = 10)
	private String zipcode;

	@Column(name = "base_address", nullable = false, length = 255)
	private String baseAddress;

	@Column(name = "detail_address", nullable = false, length = 255)
	private String detailAddress;

	@Column(nullable = false, length = 20)
	private String phone1;

	@Column(length = 20)
	private String phone2;

	@Column(name = "delivery_memo", length = 255)
	private String deliveryMemo;

	@Column(name = "is_default", nullable = false)
	private boolean isDefault;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	public static MemberAddress create(
			Member member,
			String addressName,
			String recipientName,
			String zipcode,
			String baseAddress,
			String detailAddress,
			String phone1,
			String phone2,
			String deliveryMemo,
			boolean isDefault
	) {
		MemberAddress address = new MemberAddress();
		address.member = member;
		address.addressName = addressName != null && !addressName.isBlank() ? addressName : "기본 배송지";
		address.recipientName = recipientName;
		address.zipcode = zipcode;
		address.baseAddress = baseAddress;
		address.detailAddress = detailAddress;
		address.phone1 = phone1;
		address.phone2 = phone2;
		address.deliveryMemo = deliveryMemo;
		address.isDefault = isDefault;
		return address;
	}
}
