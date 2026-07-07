package com.spharos.manbanjalbu_bo_be.domain.order.repository;

import com.spharos.manbanjalbu_bo_be.domain.order.entity.Payment;
import com.spharos.manbanjalbu_bo_be.domain.order.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query("""
			SELECT COALESCE(SUM(p.amount), 0)
			FROM Payment p
			WHERE p.status = :status
			  AND p.approvedAt >= :start
			  AND p.approvedAt < :end
			""")
	long sumAmountByStatusAndApprovedAtBetween(
			@Param("status") PaymentStatus status,
			@Param("start") LocalDateTime start,
			@Param("end") LocalDateTime end
	);

	@Query("""
			SELECT p.approvedAt, p.amount
			FROM Payment p
			WHERE p.status = :status
			  AND p.approvedAt >= :start
			  AND p.approvedAt < :end
			""")
	List<Object[]> findApprovedAmounts(
			@Param("status") PaymentStatus status,
			@Param("start") LocalDateTime start,
			@Param("end") LocalDateTime end
	);

	@Query("""
			SELECT COALESCE(SUM(p.amount), 0)
			FROM Payment p
			JOIN p.order o
			WHERE o.member.id = :memberId
			  AND p.status = :status
			""")
	long sumAmountByMemberIdAndStatus(
			@Param("memberId") Long memberId,
			@Param("status") PaymentStatus status
	);
}
