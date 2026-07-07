package com.spharos.manbanjalbu_bo_be.domain.admin.repository;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAuditLogRepository extends JpaRepository<AdminAuditLog, Long> {
}
