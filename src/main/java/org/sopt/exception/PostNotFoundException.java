package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class PostNotFoundException extends BaseException {
	public PostNotFoundException() {
		super(ErrorCode.POST_NOT_FOUND);
	}
}
