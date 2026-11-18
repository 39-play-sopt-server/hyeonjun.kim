package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class PostLikeNotFoundException extends BaseException {
	public PostLikeNotFoundException() {super(ErrorCode.POSTLIKE_NOT_FOUND);}
}
