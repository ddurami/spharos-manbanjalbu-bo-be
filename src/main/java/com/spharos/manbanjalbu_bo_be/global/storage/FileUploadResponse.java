package com.spharos.manbanjalbu_bo_be.global.storage;

public record FileUploadResponse(
		String url,
		String originalName,
		String storedName,
		long size,
		String contentType
) {
	public static FileUploadResponse from(StoredFile storedFile) {
		return new FileUploadResponse(
				storedFile.url(),
				storedFile.originalName(),
				storedFile.storedName(),
				storedFile.size(),
				storedFile.contentType()
		);
	}
}
