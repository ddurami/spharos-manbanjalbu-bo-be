package com.spharos.manbanjalbu_bo_be.global.storage;

public enum UploadCategory {
	THUMBNAIL("thumbnails"),
	EDITOR_IMAGE("editor/images"),
	EDITOR_FILE("editor/files");

	private final String directory;

	UploadCategory(String directory) {
		this.directory = directory;
	}

	public String directory() {
		return directory;
	}
}
