package com.spharos.manbanjalbu_bo_be.domain.member.repository;

import com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberListItem;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.Member;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberGrade;
import com.spharos.manbanjalbu_bo_be.domain.member.entity.MemberStatus;
import com.spharos.manbanjalbu_bo_be.domain.order.entity.PaymentStatus;
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
			""")
	long countByCreatedAtBetween(
			@Param("start") LocalDateTime start,
			@Param("end") LocalDateTime end
	);

	@Query(value = """
			SELECT new com.spharos.manbanjalbu_bo_be.domain.member.dto.MemberListItem(
			    m.id,
			    m.loginId,
			    p.name,
			    m.grade,
			    m.status,
			    m.createdAt,
			    COALESCE((SELECT SUM(pay.amount) FROM Payment pay JOIN pay.order o
			              WHERE o.member.id = m.id AND pay.status = :paidStatus), 0L)
			)
			FROM Member m
			JOIN MemberProfile p ON p.member.id = m.id
			WHERE (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%'))
			  AND (:grade IS NULL OR m.grade = :grade)
			  AND (:status IS NULL OR m.status = :status)
			  AND (:joinedFrom IS NULL OR m.createdAt >= :joinedFrom)
			  AND (:joinedTo IS NULL OR m.createdAt < :joinedTo)
			  AND (:minAmount IS NULL OR (SELECT COALESCE(SUM(pay.amount), 0L) FROM Payment pay JOIN pay.order o
			              WHERE o.member.id = m.id AND pay.status = :paidStatus) >= :minAmount)
			  AND (:maxAmount IS NULL OR (SELECT COALESCE(SUM(pay.amount), 0L) FROM Payment pay JOIN pay.order o
			              WHERE o.member.id = m.id AND pay.status = :paidStatus) <= :maxAmount)
			""",
			countQuery = """
			SELECT COUNT(m)
			FROM Member m
			JOIN MemberProfile p ON p.member.id = m.id
			WHERE (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%'))
			  AND (:grade IS NULL OR m.grade = :grade)
			  AND (:status IS NULL OR m.status = :status)
			  AND (:joinedFrom IS NULL OR m.createdAt >= :joinedFrom)
			  AND (:joinedTo IS NULL OR m.createdAt < :joinedTo)
			  AND (:minAmount IS NULL OR (SELECT COALESCE(SUM(pay.amount), 0L) FROM Payment pay JOIN pay.order o
			              WHERE o.member.id = m.id AND pay.status = :paidStatus) >= :minAmount)
			  AND (:maxAmount IS NULL OR (SELECT COALESCE(SUM(pay.amount), 0L) FROM Payment pay JOIN pay.order o
			              WHERE o.member.id = m.id AND pay.status = :paidStatus) <= :maxAmount)
			""")
	Page<MemberListItem> searchMembers(
			@Param("name") String name,
			@Param("grade") MemberGrade grade,
			@Param("status") MemberStatus status,
			@Param("joinedFrom") LocalDateTime joinedFrom,
			@Param("joinedTo") LocalDateTime joinedTo,
			@Param("minAmount") Long minAmount,
			@Param("maxAmount") Long maxAmount,
			@Param("paidStatus") PaymentStatus paidStatus,
			Pageable pageable
	);
}
