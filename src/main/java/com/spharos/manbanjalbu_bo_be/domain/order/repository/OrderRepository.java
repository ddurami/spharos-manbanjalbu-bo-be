package com.spharos.manbanjalbu_bo_be.domain.order.repository;

import com.spharos.manbanjalbu_bo_be.domain.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<Orders, Long> {

	@Query("""
			SELECT COUNT(o)
			FROM Orders o
			WHERE o.orderAt >= :start
			  AND o.orderAt < :end
			""")
	long countByOrderAtBetween(
			@Param("start") LocalDateTime start,
			@Param("end") LocalDateTime end
	);
}
