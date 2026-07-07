package com.spharos.manbanjalbu_bo_be.global.storage;

import com.spharos.manbanjalbu_bo_be.global.exception.BusinessException;
import com.spharos.manbanjalbu_bo_be.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageService {

	private static final Set<String> IMAGE_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "webp", "bmp");
	private static final Set<String> IMAGE_CONTENT_TYPES = Set.of(
			"image/jpeg", "image/png", "image/gif", "image/webp", "image/bmp"
	);
	private static final Set<String> FILE_EXTENSIONS = Set.of(
			"pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "zip", "hwp"
	);
	private static final Set<String> FILE_CONTENT_TYPES = Set.of(
			"application/pdf",
			"application/msword",
			"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
			"application/vnd.ms-excel",
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
			"application/vnd.ms-powerpoint",
			"application/vnd.openxmlformats-officedocument.presentationml.presentation",
			"text/plain",
			"application/zip",
			"application/x-hwp"
	);

	private final UploadProperties uploadProperties;

	public StoredFile storeImage(MultipartFile file, UploadCategory category) {
		validateNotEmpty(file);
		validateImage(file);
		return store(file, category);
	}

	public StoredFile storeEditorFile(MultipartFile file) {
		validateNotEmpty(file);
		validateEditorFile(file);
		return store(file, UploadCategory.EDITOR_FILE);
	}

	private StoredFile store(MultipartFile file, UploadCategory category) {
		String originalName = StringUtils.cleanPath(file.getOriginalFilename() == null ? "file" : file.getOriginalFilename());
		if (originalName.contains("..")) {
			throw new BusinessException(ErrorCode.INVALID_FILE_TYPE);
		}

		String extension = extractExtension(originalName);
		String storedName = UUID.randomUUID() + (extension.isEmpty() ? "" : "." + extension);

		Path targetDir = Path.of(uploadProperties.getBaseDir(), category.directory()).toAbsolutePath().normalize();
		Path targetFile = targetDir.resolve(storedName).normalize();

		if (!targetFile.startsWith(targetDir)) {
			throw new BusinessException(ErrorCode.INVALID_FILE_TYPE);
		}

		try {
			Files.createDirectories(targetDir);
			Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILED);
		}

		String publicPath = "/uploads/" + category.directory() + "/" + storedName;
		String url = trimTrailingSlash(uploadProperties.getPublicUrlPrefix()) + publicPath;

		return new StoredFile(
				url,
				originalName,
				storedName,
				file.getSize(),
				file.getContentType()
		);
	}

	private void validateNotEmpty(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new BusinessException(ErrorCode.FILE_EMPTY);
		}
	}

	private void validateImage(MultipartFile file) {
		String extension = extractExtension(file.getOriginalFilename());
		String contentType = file.getContentType();

		boolean validExtension = !extension.isEmpty() && IMAGE_EXTENSIONS.contains(extension);
		boolean validContentType = contentType != null && IMAGE_CONTENT_TYPES.contains(contentType.toLowerCase());

		if (!validExtension && !validContentType) {
			throw new BusinessException(ErrorCode.INVALID_FILE_TYPE);
		}
	}

	private void validateEditorFile(MultipartFile file) {
		String extension = extractExtension(file.getOriginalFilename());
		String contentType = file.getContentType();

		boolean validImage = IMAGE_EXTENSIONS.contains(extension)
				|| (contentType != null && IMAGE_CONTENT_TYPES.contains(contentType.toLowerCase()));
		boolean validFile = FILE_EXTENSIONS.contains(extension)
				|| (contentType != null && FILE_CONTENT_TYPES.contains(contentType.toLowerCase()));

		if (!validImage && !validFile) {
			throw new BusinessException(ErrorCode.INVALID_FILE_TYPE);
		}
	}

	private String extractExtension(String filename) {
		if (filename == null || !filename.contains(".")) {
			return "";
		}
		return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
	}

	private String trimTrailingSlash(String value) {
		if (value == null || value.isBlank()) {
			return "";
		}
		return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
	}
}
