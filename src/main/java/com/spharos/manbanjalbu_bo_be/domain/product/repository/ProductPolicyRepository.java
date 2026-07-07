package com.spharos.manbanjalbu_bo_be.domain.product.repository;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPolicyRepository extends JpaRepository<ProductPolicy, Long> {

	List<ProductPolicy> findAllByOrderByIdAsc();
}
