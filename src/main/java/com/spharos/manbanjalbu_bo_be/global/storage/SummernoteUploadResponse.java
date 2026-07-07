package com.spharos.manbanjalbu_bo_be.global.storage;

/**
 * Summernote 에디터 콜백 호환 응답 ({ "url": "..." }).
 */
public record SummernoteUploadResponse(String url) {
	public static SummernoteUploadResponse from(StoredFile storedFile) {
		return new SummernoteUploadResponse(storedFile.url());
	}
}
