package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class PostLikeAlreadyExistException extends BaseException {
	public PostLikeAlreadyExistException() {
		super(ErrorCode.POSTLIKE_ALREADY_EXIST);
	}
}
