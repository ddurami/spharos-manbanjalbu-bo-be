package com.spharos.manbanjalbu_bo_be.domain.admin.repository;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
