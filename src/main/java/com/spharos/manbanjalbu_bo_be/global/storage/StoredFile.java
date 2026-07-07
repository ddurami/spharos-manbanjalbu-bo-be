package com.spharos.manbanjalbu_bo_be.global.storage;

public record StoredFile(
		String url,
		String originalName,
		String storedName,
		long size,
		String contentType
) {
}
