package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class InvalidPostException extends BaseException {
	public InvalidPostException() {
		super(ErrorCode.INVALID_POST);
	}
}
