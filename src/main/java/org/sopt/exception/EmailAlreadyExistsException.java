package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class EmailAlreadyExistsException extends BaseException {
	public EmailAlreadyExistsException() {
		super(ErrorCode.EMAIL_ALREADY_EXISTS);
	}
}
