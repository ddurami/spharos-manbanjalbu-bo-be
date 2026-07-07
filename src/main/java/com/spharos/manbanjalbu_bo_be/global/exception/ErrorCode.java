package com.spharos.manbanjalbu_bo_be.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
	INVALID_INPUT(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다."),
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 올바르지 않습니다."),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
	FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
	ADMIN_INACTIVE(HttpStatus.FORBIDDEN, "비활성화된 관리자 계정입니다."),
	MEMBER_INACTIVE(HttpStatus.FORBIDDEN, "활성화되지 않은 회원입니다."),
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
	MEMBER_ALREADY_SUSPENDED(HttpStatus.BAD_REQUEST, "이미 정지된 회원입니다."),
	MEMBER_ALREADY_WITHDRAWN(HttpStatus.BAD_REQUEST, "이미 탈퇴 처리된 회원입니다."),
	MEMBER_IS_ADMIN(HttpStatus.BAD_REQUEST, "관리자 계정은 회원 관리 대상이 아닙니다."),
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
	PRODUCT_ALREADY_DELETED(HttpStatus.BAD_REQUEST, "이미 삭제된 상품입니다."),
	CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다."),
	PRODUCT_POLICY_NOT_FOUND(HttpStatus.NOT_FOUND, "상품 정책을 찾을 수 없습니다."),
	SEASON_NOT_FOUND(HttpStatus.NOT_FOUND, "시즌 정보를 찾을 수 없습니다."),
	FILE_EMPTY(HttpStatus.BAD_REQUEST, "업로드할 파일이 없습니다."),
	FILE_TOO_LARGE(HttpStatus.BAD_REQUEST, "파일 크기가 허용 범위를 초과했습니다."),
	INVALID_FILE_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 파일 형식입니다."),
	FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");

	private final HttpStatus status;
	private final String message;

	ErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}
