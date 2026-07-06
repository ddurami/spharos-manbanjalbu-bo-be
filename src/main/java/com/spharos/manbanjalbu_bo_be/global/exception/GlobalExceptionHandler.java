package com.spharos.manbanjalbu_bo_be.global.exception;

import com.spharos.manbanjalbu_bo_be.global.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException exception) {
		ErrorCode errorCode = exception.getErrorCode();
		return ResponseEntity
				.status(errorCode.getStatus())
				.body(ApiResponse.fail(exception.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException exception) {
		String message = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(", "));
		return ResponseEntity.badRequest().body(ApiResponse.fail(message));
	}

	@ExceptionHandler({BadCredentialsException.class, AuthenticationException.class})
	public ResponseEntity<ApiResponse<Void>> handleAuthenticationException(AuthenticationException exception) {
		return ResponseEntity
				.status(ErrorCode.INVALID_CREDENTIALS.getStatus())
				.body(ApiResponse.fail(ErrorCode.INVALID_CREDENTIALS.getMessage()));
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<ApiResponse<Void>> handleDisabledException(DisabledException exception) {
		return ResponseEntity
				.status(ErrorCode.ADMIN_INACTIVE.getStatus())
				.body(ApiResponse.fail(exception.getMessage()));
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<Void>> handleAccessDeniedException(AccessDeniedException exception) {
		return ResponseEntity
				.status(ErrorCode.FORBIDDEN.getStatus())
				.body(ApiResponse.fail(ErrorCode.FORBIDDEN.getMessage()));
	}
}
