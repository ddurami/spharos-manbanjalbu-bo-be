package com.spharos.manbanjalbu_bo_be.domain.admin.repository;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Query("""
			SELECT a FROM Admin a
			JOIN FETCH a.member
			JOIN FETCH a.role
			WHERE a.memberId = :memberId
			""")
	Optional<Admin> findWithDetailsByMemberId(@Param("memberId") Long memberId);
}
