package com.spharos.manbanjalbu_bo_be.domain.member.repository;

import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
			  AND NOT EXISTS (SELECT 1 FROM Admin a WHERE a.memberId = m.id)
			""")
	long countByCreatedAtBetween(
			@Param("start") LocalDateTime start,
			@Param("end") LocalDateTime end
	);

	@Query("""
			SELECT COUNT(m)
			FROM Member m
			WHERE NOT EXISTS (SELECT 1 FROM Admin a WHERE a.memberId = m.id)
			""")
	long countCustomerMembers();

	@Query("""
			SELECT COUNT(m)
			FROM Member m
			WHERE m.status = :status
			  AND NOT EXISTS (SELECT 1 FROM Admin a WHERE a.memberId = m.id)
			""")
	long countByStatusExcludingAdmin(@Param("status") MemberStatus status);

	@Query("""
			SELECT m FROM Member m
			JOIN MemberProfile p ON p.memberId = m.id
			WHERE NOT EXISTS (SELECT 1 FROM Admin a WHERE a.memberId = m.id)
			  AND (:keyword IS NULL OR (
			        LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
			     OR LOWER(p.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
			     OR p.phone LIKE CONCAT('%', :keyword, '%')
			     OR LOWER(m.loginId) LIKE LOWER(CONCAT('%', :keyword, '%'))
			  ))
			  AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
			  AND (:grade IS NULL OR m.grade = :grade)
			  AND (:status IS NULL OR m.status = :status)
			  AND (:createdFrom IS NULL OR m.createdAt >= :createdFrom)
			  AND (:createdTo IS NULL OR m.createdAt < :createdTo)
			  AND (:minAmount IS NULL OR (
			        SELECT COALESCE(SUM(o.orderAmount), 0)
			        FROM Orders o
			        WHERE o.member = m
			      ) >= :minAmount)
			  AND (:maxAmount IS NULL OR (
			        SELECT COALESCE(SUM(o.orderAmount), 0)
			        FROM Orders o
			        WHERE o.member = m
			      ) <= :maxAmount)
			""")
	Page<Member> searchMembers(
			@Param("keyword") String keyword,
			@Param("name") String name,
			@Param("grade") MemberGrade grade,
			@Param("status") MemberStatus status,
			@Param("createdFrom") LocalDateTime createdFrom,
			@Param("createdTo") LocalDateTime createdTo,
			@Param("minAmount") Long minAmount,
			@Param("maxAmount") Long maxAmount,
			Pageable pageable
	);
}
