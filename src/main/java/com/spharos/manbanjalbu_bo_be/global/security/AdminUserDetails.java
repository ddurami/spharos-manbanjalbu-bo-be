package com.spharos.manbanjalbu_bo_be.global.security;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminRole;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminStatus;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class AdminUserDetails implements UserDetails {

	private final Member member;
	private final Admin admin;
	private final Collection<? extends GrantedAuthority> authorities;

	public AdminUserDetails(Member member, Admin admin) {
		this.member = member;
		this.admin = admin;
		this.authorities = buildAuthorities(admin.getRole());
	}

	private static List<GrantedAuthority> buildAuthorities(AdminRole role) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));

		if (role.isCanManageProduct()) {
			authorities.add(new SimpleGrantedAuthority("MANAGE_PRODUCT"));
		}
		if (role.isCanManageMember()) {
			authorities.add(new SimpleGrantedAuthority("MANAGE_MEMBER"));
		}
		if (role.isCanManageOrder()) {
			authorities.add(new SimpleGrantedAuthority("MANAGE_ORDER"));
		}
		if (role.isCanManageSystem()) {
			authorities.add(new SimpleGrantedAuthority("MANAGE_SYSTEM"));
		}
		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return admin.getAdminStatus() == AdminStatus.ACTIVE;
	}
}
