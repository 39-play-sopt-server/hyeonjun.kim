package org.sopt.exception.handler;

import org.sopt.exception.BaseException;
import org.sopt.response.ApiResponse;
import org.sopt.response.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ApiResponse<Void>> handleBaseException(BaseException e) {
		ApiResponse<Void> response = ApiResponse.failure(e.getErrorCode());

		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e
	) {
		String message = e.getBindingResult()
			.getFieldErrors()
			.get(0)
			.getDefaultMessage();

		ApiResponse<String> response = ApiResponse.failure(ErrorCode.INVALID_INPUT, message);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<ApiResponse<Void>> handleHandlerMethodValidationException(
		HandlerMethodValidationException e
	) {
		ApiResponse<Void> response = ApiResponse.failure(ErrorCode.INVALID_INPUT);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse<Void>> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException e
	) {
		ApiResponse<Void> response = ApiResponse.failure(ErrorCode.INVALID_INPUT);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(
		HttpMessageNotReadableException e
	) {
		ApiResponse<Void> response = ApiResponse.failure(ErrorCode.INVALID_INPUT);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiResponse<Void>> handleMissingServletRequestParameterException(
		MissingServletRequestParameterException e
	) {
		ApiResponse<Void> response = ApiResponse.failure(ErrorCode.INVALID_INPUT);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(MissingRequestCookieException.class)
	public ResponseEntity<ApiResponse<Void>> handleMissingRequestCookieException(MissingRequestCookieException e) {
		return errorResponse(ErrorCode.INVALID_REFRESH_TOKEN);
	}

	private ResponseEntity<ApiResponse<Void>> errorResponse(ErrorCode errorCode) {
		ApiResponse<Void> response = ApiResponse.failure(errorCode);

		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}
}
