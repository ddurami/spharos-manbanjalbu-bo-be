package com.spharos.manbanjalbu_bo_be.domain.product.service;

import com.spharos.manbanjalbu_bo_be.domain.admin.entity.Admin;
import com.spharos.manbanjalbu_bo_be.domain.admin.entity.AdminAuditLog;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminAuditLogRepository;
import com.spharos.manbanjalbu_bo_be.domain.admin.repository.AdminRepository;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.CategoryPathItem;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.CategoryResponse;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductCreateRequest;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductDetailResponse;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductListItem;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductMediaResponse;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductPolicyResponse;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductSummaryResponse;
import com.spharos.manbanjalbu_bo_be.domain.product.dto.ProductUpdateRequest;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.Category;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.Product;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductMedia;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductMediaType;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductPolicy;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductSalesSummary;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductSaleType;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.ProductStatus;
import com.spharos.manbanjalbu_bo_be.domain.product.entity.Season;
import com.spharos.manbanjalbu_bo_be.domain.product.repository.CategoryRepository;
import com.spharos.manbanjalbu_bo_be.domain.product.repository.ProductMediaRepository;
import com.spharos.manbanjalbu_bo_be.domain.product.repository.ProductPolicyRepository;
import com.spharos.manbanjalbu_bo_be.domain.product.repository.ProductRepository;
import com.spharos.manbanjalbu_bo_be.domain.product.repository.ProductSalesSummaryRepository;
import com.spharos.manbanjalbu_bo_be.domain.product.repository.SeasonRepository;
import com.spharos.manbanjalbu_bo_be.global.exception.BusinessException;
import com.spharos.manbanjalbu_bo_be.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManagementService {

	private final ProductRepository productRepository;
	private final ProductMediaRepository productMediaRepository;
	private final ProductSalesSummaryRepository productSalesSummaryRepository;
	private final CategoryRepository categoryRepository;
	private final ProductPolicyRepository productPolicyRepository;
	private final SeasonRepository seasonRepository;
	private final AdminRepository adminRepository;
	private final AdminAuditLogRepository adminAuditLogRepository;

	@Transactional(readOnly = true)
	public ProductSummaryResponse getSummary() {
		long total = productRepository.countByDeletedFalse();
		long onSale = 0;
		long soldOut = 0;
		long hidden = 0;
		for (Object[] row : productRepository.countGroupByStatus()) {
			ProductStatus status = (ProductStatus) row[0];
			long count = (Long) row[1];
			switch (status) {
				case ON_SALE -> onSale = count;
				case SOLD_OUT -> soldOut = count;
				case HIDDEN -> hidden = count;
			}
		}
		long deleted = productRepository.countByDeletedTrue();
		return new ProductSummaryResponse(total, onSale, soldOut, hidden, deleted);
	}

	@Transactional(readOnly = true)
	public Page<ProductListItem> searchProducts(
			String name,
			Long categoryId,
			ProductStatus status,
			Integer minPrice,
			Integer maxPrice,
			LocalDateTime createdFrom,
			LocalDateTime createdTo,
			Pageable pageable
	) {
		Page<Product> page = productRepository.search(
				emptyToNull(name), categoryId, status, minPrice, maxPrice, createdFrom, createdTo, pageable);

		List<Long> productIds = page.getContent().stream().map(Product::getId).toList();
		Map<Long, String> thumbnailMap = buildThumbnailMap(productIds);
		Map<Long, Integer> salesMap = buildSalesMap(productIds);

		return page.map(product -> new ProductListItem(
				product.getId(),
				product.getName(),
				thumbnailMap.get(product.getId()),
				product.getCategory() == null ? null : product.getCategory().getName(),
				product.getPrice(),
				product.getStatus(),
				product.getSaleType(),
				buildBadges(product),
				salesMap.getOrDefault(product.getId(), 0),
				product.getCreatedAt()
		));
	}

	@Transactional(readOnly = true)
	public ProductDetailResponse getProductDetail(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

		List<ProductMedia> mediaList = productMediaRepository.findByProduct_IdOrderByDisplayOrderAscIdAsc(productId);

		String thumbnailUrl = mediaList.stream()
				.filter(m -> m.getMediaType() == ProductMediaType.THUMBNAIL)
				.sorted((a, b) -> Boolean.compare(b.isMain(), a.isMain()))
				.map(ProductMedia::getMediaUrl)
				.findFirst()
				.orElse(null);

		List<ProductMediaResponse> detailImages = mediaList.stream()
				.filter(m -> m.getMediaType() == ProductMediaType.DETAIL_IMAGE)
				.map(ProductMediaResponse::from)
				.toList();

		String detailHtml = mediaList.stream()
				.filter(m -> m.getMediaType() == ProductMediaType.DETAIL_HTML)
				.map(ProductMedia::getMediaUrl)
				.findFirst()
				.orElse(null);

		int salesCount = productSalesSummaryRepository.findById(productId)
				.map(ProductSalesSummary::getTotalSalesCount)
				.orElse(0);

		Category category = product.getCategory();
		Season season = product.getSeason();
		ProductPolicy policy = product.getPolicy();

		return new ProductDetailResponse(
				product.getId(),
				product.getName(),
				product.getShortDescription(),
				product.getPrice(),
				product.getStatus(),
				product.isDeleted(),
				product.getSaleType(),
				product.isBest(),
				product.isNew(),
				buildBadges(product),
				product.getCapacity(),
				season == null ? null : season.getId(),
				season == null ? null : season.getName(),
				category == null ? null : category.getId(),
				buildCategoryPath(category),
				policy == null ? null : policy.getId(),
				policy == null ? null : policy.getTitle(),
				salesCount,
				thumbnailUrl,
				detailImages,
				detailHtml,
				product.getStatusChangeReason(),
				product.getCreatedAt(),
				product.getUpdatedAt()
		);
	}

	@Transactional
	public ProductDetailResponse createProduct(ProductCreateRequest request) {
		Category category = findCategory(request.categoryId());
		ProductPolicy policy = findPolicy(request.policyId());
		Season season = findSeason(request.seasonId());
		ProductStatus status = request.status() != null ? request.status() : ProductStatus.ON_SALE;

		Product product = Product.builder()
				.category(category)
				.policy(policy)
				.name(request.name())
				.shortDescription(request.shortDescription())
				.price(request.price())
				.saleType(request.saleType())
				.season(season)
				.capacity(request.capacity())
				.best(request.best())
				.isNew(request.isNew())
				.status(status)
				.build();
		Product saved = productRepository.save(product);
		saveProductMedia(saved, request.thumbnailUrl(), request.detailImageUrls(), request.detailHtml());

		return getProductDetail(saved.getId());
	}

	@Transactional
	public ProductDetailResponse updateProduct(Long productId, ProductUpdateRequest request) {
		Product product = getActiveProduct(productId);
		Category category = findCategory(request.categoryId());
		ProductPolicy policy = findPolicy(request.policyId());
		Season season = findSeason(request.seasonId());
		ProductStatus status = request.status() != null ? request.status() : product.getStatus();

		product.updateInfo(
				category,
				policy,
				request.name(),
				request.shortDescription(),
				request.price(),
				request.saleType(),
				season,
				request.capacity(),
				request.best(),
				request.isNew(),
				status
		);

		productMediaRepository.deleteByProduct_Id(productId);
		saveProductMedia(product, request.thumbnailUrl(), request.detailImageUrls(), request.detailHtml());

		return getProductDetail(productId);
	}

	@Transactional
	public void markSoldOut(Long productId, String reason, Admin admin, String ip) {
		Product product = getActiveProduct(productId);
		product.markSoldOut(reason, admin.getMemberId());
		recordAuditLog(admin, "PRODUCT_SOLD_OUT", productId, "상품 품절 처리: " + reason, ip);
	}

	@Transactional
	public void stopSelling(Long productId, String reason, Admin admin, String ip) {
		Product product = getActiveProduct(productId);
		product.stopSelling(reason, admin.getMemberId());
		recordAuditLog(admin, "PRODUCT_STOP_SELLING", productId, "상품 판매 중지: " + reason, ip);
	}

	@Transactional
	public void deleteProduct(Long productId, String reason, Admin admin, String ip) {
		Product product = getActiveProduct(productId);
		product.softDelete(reason, admin.getMemberId());
		recordAuditLog(admin, "PRODUCT_DELETE", productId, "상품 삭제: " + reason, ip);
	}

	@Transactional(readOnly = true)
	public List<CategoryResponse> getCategories() {
		return categoryRepository.findAllByOrderByDepthAscDisplayOrderAsc().stream()
				.map(CategoryResponse::from)
				.toList();
	}

	@Transactional(readOnly = true)
	public List<ProductPolicyResponse> getPolicies() {
		return productPolicyRepository.findAllByOrderByIdAsc().stream()
				.map(ProductPolicyResponse::from)
				.toList();
	}

	private Product getActiveProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
		if (product.isDeleted()) {
			throw new BusinessException(ErrorCode.PRODUCT_ALREADY_DELETED);
		}
		return product;
	}

	private Map<Long, String> buildThumbnailMap(List<Long> productIds) {
		if (productIds.isEmpty()) {
			return Map.of();
		}
		List<ProductMedia> thumbnails =
				productMediaRepository.findByProduct_IdInAndMediaType(productIds, ProductMediaType.THUMBNAIL);
		Map<Long, String> map = new java.util.HashMap<>();
		for (ProductMedia media : thumbnails) {
			Long pid = media.getProduct().getId();
			if (media.isMain() || !map.containsKey(pid)) {
				map.put(pid, media.getMediaUrl());
			}
		}
		return map;
	}

	private Map<Long, Integer> buildSalesMap(List<Long> productIds) {
		if (productIds.isEmpty()) {
			return Map.of();
		}
		return productSalesSummaryRepository.findByProductIdIn(productIds).stream()
				.collect(Collectors.toMap(
						ProductSalesSummary::getProductId,
						ProductSalesSummary::getTotalSalesCount));
	}

	private List<String> buildBadges(Product product) {
		List<String> badges = new ArrayList<>();
		if (product.isBest()) {
			badges.add("BEST");
		}
		if (product.isNew()) {
			badges.add("NEW");
		}
		if (product.getSaleType() == ProductSaleType.LIMITED) {
			badges.add("LIMITED");
		}
		if (product.getSaleType() == ProductSaleType.SEASON) {
			badges.add("SEASON");
		}
		return badges;
	}

	private List<CategoryPathItem> buildCategoryPath(Category category) {
		List<CategoryPathItem> path = new ArrayList<>();
		Category current = category;
		while (current != null) {
			path.add(0, new CategoryPathItem(current.getId(), current.getName(), current.getDepth()));
			current = current.getParent();
		}
		return path;
	}

	private void recordAuditLog(Admin admin, String actionType, Long targetId, String description, String ip) {
		AdminAuditLog log = AdminAuditLog.builder()
				.admin(adminRepository.getReferenceById(admin.getMemberId()))
				.actionType(actionType)
				.targetId(targetId)
				.description(truncate(description, 255))
				.ipAddress(ip)
				.build();
		adminAuditLogRepository.save(log);
	}

	private String truncate(String value, int max) {
		if (value == null) {
			return null;
		}
		return value.length() <= max ? value : value.substring(0, max);
	}

	private String emptyToNull(String value) {
		return (value == null || value.isBlank()) ? null : value;
	}

	private Category findCategory(Long categoryId) {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));
	}

	private ProductPolicy findPolicy(Long policyId) {
		return productPolicyRepository.findById(policyId)
				.orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_POLICY_NOT_FOUND));
	}

	private Season findSeason(Long seasonId) {
		if (seasonId == null) {
			return null;
		}
		return seasonRepository.findById(seasonId)
				.orElseThrow(() -> new BusinessException(ErrorCode.SEASON_NOT_FOUND));
	}

	private void saveProductMedia(
			Product product,
			String thumbnailUrl,
			List<String> detailImageUrls,
			String detailHtml
	) {
		List<ProductMedia> mediaList = new ArrayList<>();
		mediaList.add(ProductMedia.builder()
				.product(product)
				.mediaType(ProductMediaType.THUMBNAIL)
				.mediaUrl(thumbnailUrl)
				.displayOrder(0)
				.main(true)
				.build());

		if (detailImageUrls != null) {
			int order = 1;
			for (String url : detailImageUrls) {
				if (url == null || url.isBlank()) {
					continue;
				}
				mediaList.add(ProductMedia.builder()
						.product(product)
						.mediaType(ProductMediaType.DETAIL_IMAGE)
						.mediaUrl(url)
						.displayOrder(order++)
						.main(false)
						.build());
			}
		}

		if (detailHtml != null && !detailHtml.isBlank()) {
			mediaList.add(ProductMedia.builder()
					.product(product)
					.mediaType(ProductMediaType.DETAIL_HTML)
					.mediaUrl(detailHtml)
					.displayOrder(0)
					.main(false)
					.build());
		}

		productMediaRepository.saveAll(mediaList);
	}
}
