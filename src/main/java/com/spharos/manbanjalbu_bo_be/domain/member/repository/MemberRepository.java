package com.spharos.manbanjalbu_bo_be.domain.member.repository;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByLoginId(String loginId);

	boolean existsByLoginId(String loginId);

	@Query("""
			SELECT COUNT(m)
			FROM Member m
			WHERE m.createdAt >= :start
			  AND m.createdAt < :end
			""")
	long countByCreatedAtBetween(
			@Param("start") LocalDateTime start,
			@Param("end") LocalDateTime end
	);
}
