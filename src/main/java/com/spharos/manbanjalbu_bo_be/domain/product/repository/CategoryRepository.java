package com.spharos.manbanjalbu_bo_be.domain.product.repository;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findAllByOrderByDepthAscDisplayOrderAsc();
}
