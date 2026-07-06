package com.spharos.manbanjalbu_bo_be.domain.admin.repository;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRoleRepository extends JpaRepository<AdminRole, Long> {

	Optional<AdminRole> findByRoleName(String roleName);
}
