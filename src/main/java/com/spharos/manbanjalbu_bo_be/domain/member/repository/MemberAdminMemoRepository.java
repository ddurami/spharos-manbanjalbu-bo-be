package com.spharos.manbanjalbu_bo_be.domain.member.repository;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberAdminMemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAdminMemoRepository extends JpaRepository<MemberAdminMemo, Long> {
}
