package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException() {
		super(ErrorCode.POST_NOT_FOUND.getMessage());
	}
}
