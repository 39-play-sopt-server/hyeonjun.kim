package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class PostNotOwnedException extends RuntimeException {
	public PostNotOwnedException() {
		super(ErrorCode.POST_NOT_OWNED.getMessage());
	}
}
