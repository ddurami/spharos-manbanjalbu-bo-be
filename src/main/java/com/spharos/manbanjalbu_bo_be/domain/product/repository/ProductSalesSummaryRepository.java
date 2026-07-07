package com.spharos.manbanjalbu_bo_be.domain.product.repository;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductSalesSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSalesSummaryRepository extends JpaRepository<ProductSalesSummary, Long> {

	List<ProductSalesSummary> findByProductIdIn(List<Long> productIds);
}
