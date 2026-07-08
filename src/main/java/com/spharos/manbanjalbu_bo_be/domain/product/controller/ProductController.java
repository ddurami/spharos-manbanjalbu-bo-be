package com.spharos.manbanjalbu_bo_be.domain.product.controller;

import com.spharos.manbanjalbu_bo_be.domain.product.dto.CategoryResponse;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductCreateRequest;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductDetailResponse;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductListItem;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductPolicyResponse;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductReasonRequest;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductSummaryResponse;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductUpdateRequest;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.SeasonResponse;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductStatus;
import com.spharos.manbanjalbu_bo_be.domain.product.service.ProductManagementService;
import com.spharos.manbanjalbu_bo_be.global.common.ApiResponse;
import com.spharos.manbanjalbu_bo_be.global.security.AdminUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductManagementService productManagementService;

	@GetMapping("/summary")
	public ResponseEntity<ApiResponse<ProductSummaryResponse>> getSummary() {
		return ResponseEntity.ok(ApiResponse.ok(productManagementService.getSummary()));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Page<ProductListItem>>> searchProducts(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Long categoryId,
			@RequestParam(required = false) ProductStatus status,
			@RequestParam(required = false) Integer minPrice,
			@RequestParam(required = false) Integer maxPrice,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registeredFrom,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registeredTo,
			@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		LocalDateTime createdFrom = registeredFrom != null ? registeredFrom.atStartOfDay() : null;
		LocalDateTime createdTo = registeredTo != null ? registeredTo.plusDays(1).atStartOfDay() : null;

		Page<ProductListItem> result = productManagementService.searchProducts(
				name, categoryId, status, minPrice, maxPrice, createdFrom, createdTo, pageable);
		return ResponseEntity.ok(ApiResponse.ok(result));
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ApiResponse<ProductDetailResponse>> getProductDetail(@PathVariable Long productId) {
		return ResponseEntity.ok(ApiResponse.ok(productManagementService.getProductDetail(productId)));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<ProductDetailResponse>> createProduct(
			@Valid @RequestBody ProductCreateRequest request
	) {
		ProductDetailResponse created = productManagementService.createProduct(request);
		return ResponseEntity.ok(ApiResponse.ok(created));
	}

	@PutMapping("/{productId}")
	public ResponseEntity<ApiResponse<ProductDetailResponse>> updateProduct(
			@PathVariable Long productId,
			@Valid @RequestBody ProductUpdateRequest request
	) {
		ProductDetailResponse updated = productManagementService.updateProduct(productId, request);
		return ResponseEntity.ok(ApiResponse.ok(updated));
	}

	@PostMapping("/{productId}/sold-out")
	public ResponseEntity<ApiResponse<Void>> markSoldOut(
			@PathVariable Long productId,
			@Valid @RequestBody ProductReasonRequest request,
			@AuthenticationPrincipal AdminUserDetails userDetails,
			HttpServletRequest httpRequest
	) {
		productManagementService.markSoldOut(
				productId, request.reason(), userDetails.getAdmin(), getClientIp(httpRequest));
		return ResponseEntity.ok(ApiResponse.ok(null));
	}

	@PostMapping("/{productId}/stop-selling")
	public ResponseEntity<ApiResponse<Void>> stopSelling(
			@PathVariable Long productId,
			@Valid @RequestBody ProductReasonRequest request,
			@AuthenticationPrincipal AdminUserDetails userDetails,
			HttpServletRequest httpRequest
	) {
		productManagementService.stopSelling(
				productId, request.reason(), userDetails.getAdmin(), getClientIp(httpRequest));
		return ResponseEntity.ok(ApiResponse.ok(null));
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<ApiResponse<Void>> deleteProduct(
			@PathVariable Long productId,
			@Valid @RequestBody ProductReasonRequest request,
			@AuthenticationPrincipal AdminUserDetails userDetails,
			HttpServletRequest httpRequest
	) {
		productManagementService.deleteProduct(
				productId, request.reason(), userDetails.getAdmin(), getClientIp(httpRequest));
		return ResponseEntity.ok(ApiResponse.ok(null));
	}

	@GetMapping("/categories")
	public ResponseEntity<ApiResponse<List<CategoryResponse>>> getCategories() {
		return ResponseEntity.ok(ApiResponse.ok(productManagementService.getCategories()));
	}

	@GetMapping("/policies")
	public ResponseEntity<ApiResponse<List<ProductPolicyResponse>>> getPolicies() {
		return ResponseEntity.ok(ApiResponse.ok(productManagementService.getPolicies()));
	}

	@GetMapping("/seasons")
	public ResponseEntity<ApiResponse<List<SeasonResponse>>> getSeasons() {
		return ResponseEntity.ok(ApiResponse.ok(productManagementService.getSeasons()));
	}

	private String getClientIp(HttpServletRequest request) {
		String forwarded = request.getHeader("X-Forwarded-For");
		if (forwarded != null && !forwarded.isBlank()) {
			return forwarded.split(",")[0].trim();
		}
		return request.getRemoteAddr();
	}
}
