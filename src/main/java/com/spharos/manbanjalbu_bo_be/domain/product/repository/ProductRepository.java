package com.spharos.manbanjalbu_bo_be.domain.product.repository;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.Product;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = """
			SELECT p FROM Product p
			LEFT JOIN FETCH p.category c
			WHERE p.deleted = false
			  AND (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%'))
			  AND (:categoryId IS NULL OR c.id = :categoryId)
			  AND (:status IS NULL OR p.status = :status)
			  AND (:minPrice IS NULL OR p.price >= :minPrice)
			  AND (:maxPrice IS NULL OR p.price <= :maxPrice)
			  AND (:createdFrom IS NULL OR p.createdAt >= :createdFrom)
			  AND (:createdTo IS NULL OR p.createdAt < :createdTo)
			""",
			countQuery = """
			SELECT COUNT(p) FROM Product p
			WHERE p.deleted = false
			  AND (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%'))
			  AND (:categoryId IS NULL OR p.category.id = :categoryId)
			  AND (:status IS NULL OR p.status = :status)
			  AND (:minPrice IS NULL OR p.price >= :minPrice)
			  AND (:maxPrice IS NULL OR p.price <= :maxPrice)
			  AND (:createdFrom IS NULL OR p.createdAt >= :createdFrom)
			  AND (:createdTo IS NULL OR p.createdAt < :createdTo)
			""")
	Page<Product> search(
			@Param("name") String name,
			@Param("categoryId") Long categoryId,
			@Param("status") ProductStatus status,
			@Param("minPrice") Integer minPrice,
			@Param("maxPrice") Integer maxPrice,
			@Param("createdFrom") LocalDateTime createdFrom,
			@Param("createdTo") LocalDateTime createdTo,
			Pageable pageable
	);

	Optional<Product> findByIdAndDeletedFalse(Long id);

	long countByDeletedFalse();

	long countByDeletedTrue();

	@Query("""
			SELECT p.status, COUNT(p)
			FROM Product p
			WHERE p.deleted = false
			GROUP BY p.status
			""")
	List<Object[]> countGroupByStatus();
}
