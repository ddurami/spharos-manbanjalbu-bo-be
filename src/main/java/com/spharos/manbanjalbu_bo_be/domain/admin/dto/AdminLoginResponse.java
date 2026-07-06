package com.spharos.manbanjalbu_bo_be.domain.admin.dto;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminLoginResponse {

	private Long memberId;
	private String loginId;
	private String roleName;
	private boolean canManageProduct;
	private boolean canManageMember;
	private boolean canManageOrder;
	private boolean canManageSystem;

	public static AdminLoginResponse from(Admin admin) {
		AdminRole role = admin.getRole();
		return AdminLoginResponse.builder()
				.memberId(admin.getMemberId())
				.loginId(admin.getMember().getLoginId())
				.roleName(role.getRoleName())
				.canManageProduct(role.isCanManageProduct())
				.canManageMember(role.isCanManageMember())
				.canManageOrder(role.isCanManageOrder())
				.canManageSystem(role.isCanManageSystem())
				.build();
	}
}
