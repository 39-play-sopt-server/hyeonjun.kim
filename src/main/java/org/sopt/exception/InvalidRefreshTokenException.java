package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class InvalidRefreshTokenException extends BaseException {
	public InvalidRefreshTokenException() {
		super(ErrorCode.INVALID_REFRESH_TOKEN);
	}
}
