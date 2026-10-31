package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class PostLikeNotFoundException extends RuntimeException {
	public PostLikeNotFoundException() {super(ErrorCode.POSTLIKE_NOT_FOUND.getMessage());}
}
