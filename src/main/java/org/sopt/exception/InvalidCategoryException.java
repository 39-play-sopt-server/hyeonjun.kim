package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class InvalidCategoryException extends BaseException {
	public InvalidCategoryException() {
		super(ErrorCode.INVALID_CATEGORY);
	}
}
