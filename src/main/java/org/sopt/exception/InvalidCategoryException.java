package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class InvalidCategoryException extends RuntimeException {
	public InvalidCategoryException() {
		super(ErrorCode.INVALID_CATEGORY.getMessage());
	}
}
