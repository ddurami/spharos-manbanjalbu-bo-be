package com.spharos.manbanjalbu_bo_be.domain.member.dto;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberAddress;

public record MemberAddressResponse(
		Long addressId,
		String addressName,
		String recipientName,
		String zipcode,
		String baseAddress,
		String detailAddress,
		String phone1,
		String phone2,
		boolean isDefault
) {
	public static MemberAddressResponse from(MemberAddress address) {
		return new MemberAddressResponse(
				address.getId(),
				address.getAddressName(),
				address.getRecipientName(),
				address.getZipcode(),
				address.getBaseAddress(),
				address.getDetailAddress(),
				address.getPhone1(),
				address.getPhone2(),
				address.isDefault()
		);
	}
}
