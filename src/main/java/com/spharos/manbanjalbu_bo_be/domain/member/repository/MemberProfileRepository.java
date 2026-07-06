package com.spharos.manbanjalbu_bo_be.domain.member.repository;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
}
