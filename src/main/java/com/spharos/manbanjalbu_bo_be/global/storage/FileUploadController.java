package com.spharos.manbanjalbu_bo_be.global.storage;

import com.spharos.manbanjalbu_bo_be.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "파일 업로드", description = "상품 썸네일 및 Summernote 에디터 파일 업로드")
@RestController
@RequestMapping("/api/admin/files")
@RequiredArgsConstructor
public class FileUploadController {

	private final FileStorageService fileStorageService;

	@Operation(summary = "상품 썸네일 업로드", description = "상품 등록/수정 시 썸네일 이미지 업로드")
	@PostMapping(value = "/thumbnail", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse<FileUploadResponse>> uploadThumbnail(
			@RequestPart("file") MultipartFile file
	) {
		StoredFile stored = fileStorageService.storeImage(file, UploadCategory.THUMBNAIL);
		return ResponseEntity.ok(ApiResponse.ok(FileUploadResponse.from(stored)));
	}

	@Operation(summary = "Summernote 이미지 업로드", description = "Summernote 에디터 본문 이미지 삽입용")
	@PostMapping(value = "/summernote/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<SummernoteUploadResponse> uploadSummernoteImage(
			@RequestPart("file") MultipartFile file
	) {
		StoredFile stored = fileStorageService.storeImage(file, UploadCategory.EDITOR_IMAGE);
		return ResponseEntity.ok(SummernoteUploadResponse.from(stored));
	}

	@Operation(summary = "Summernote 파일 업로드", description = "Summernote 에디터 첨부 파일 업로드")
	@PostMapping(value = "/summernote/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<SummernoteUploadResponse> uploadSummernoteFile(
			@RequestPart("file") MultipartFile file
	) {
		StoredFile stored = fileStorageService.storeEditorFile(file);
		return ResponseEntity.ok(SummernoteUploadResponse.from(stored));
	}
}
