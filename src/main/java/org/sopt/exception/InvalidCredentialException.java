package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class InvalidCredentialException extends BaseException {
	public InvalidCredentialException() {
		super(ErrorCode.INVALID_CREDENTIALS);
	}
}
