package com.spharos.manbanjalbu_bo_be.domain.member.repository;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {

	List<MemberAddress> findByMember_IdOrderByIsDefaultDescCreatedAtDesc(Long memberId);
}
