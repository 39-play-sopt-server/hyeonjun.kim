package org.sopt.exception.handler;

import org.sopt.exception.InvalidCategoryException;
import org.sopt.exception.InvalidPostException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.response.ApiResponse;
import org.sopt.response.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidCategoryException.class)
	public ResponseEntity<ApiResponse<Void>> handleInvalidCategoryException(
		InvalidCategoryException e) {
		return errorResponse(ErrorCode.INVALID_CATEGORY);
	}

	@ExceptionHandler(InvalidPostException.class)
	public ResponseEntity<ApiResponse<Void>> handleInvalidPostException(
		InvalidPostException e) {
		return errorResponse(ErrorCode.INVALID_POST);
	}

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handlePostNotFoundException(
		PostNotFoundException e) {
		return errorResponse(ErrorCode.POST_NOT_FOUND);
	}

	private ResponseEntity<ApiResponse<Void>> errorResponse(ErrorCode errorCode) {
		ApiResponse<Void> response = ApiResponse.failure(errorCode);

		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}
}
