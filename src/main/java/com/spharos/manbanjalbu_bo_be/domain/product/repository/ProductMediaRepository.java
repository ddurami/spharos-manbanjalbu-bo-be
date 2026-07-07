package com.spharos.manbanjalbu_bo_be.domain.product.repository;

import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductMedia;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductMediaType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductMediaRepository extends JpaRepository<ProductMedia, Long> {

	List<ProductMedia> findByProduct_IdOrderByDisplayOrderAscIdAsc(Long productId);

	List<ProductMedia> findByProduct_IdInAndMediaType(List<Long> productIds, ProductMediaType mediaType);

	void deleteByProduct_Id(Long productId);
}
