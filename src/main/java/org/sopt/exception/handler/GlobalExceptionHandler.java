package org.sopt.exception.handler;

import org.sopt.exception.InvalidCategoryException;
import org.sopt.exception.InvalidPostException;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.exception.PostLikeAlreadyExistException;
import org.sopt.exception.PostLikeNotFoundException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.exception.PostNotOwnedException;
import org.sopt.response.ApiResponse;
import org.sopt.response.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleMemberNotFoundException(
		MemberNotFoundException e) {
		return errorResponse(ErrorCode.MEMBER_NOT_FOUND);
	}

	@ExceptionHandler(PostNotOwnedException.class)
	public ResponseEntity<ApiResponse<Void>> handlePostNotOwnedException(
		PostNotOwnedException e) {
		return errorResponse(ErrorCode.POST_NOT_OWNED);
	}

	@ExceptionHandler(PostLikeNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handlePostLikeNotFoundException(
		PostLikeNotFoundException e
	) {
		return errorResponse(ErrorCode.POSTLIKE_NOT_FOUND);
	}

	@ExceptionHandler(PostLikeAlreadyExistException.class)
	public ResponseEntity<ApiResponse<Void>> handlePostLikeAlreadyExistException(
		PostLikeAlreadyExistException e
	) {
		return errorResponse(ErrorCode.POSTLIKE_ALREADY_EXIST);
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

	private ResponseEntity<ApiResponse<Void>> errorResponse(ErrorCode errorCode) {
		ApiResponse<Void> response = ApiResponse.failure(errorCode);

		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}
}
