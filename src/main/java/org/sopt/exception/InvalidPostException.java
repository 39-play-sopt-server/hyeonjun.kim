package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class InvalidPostException extends RuntimeException {
	public InvalidPostException() {
		super(ErrorCode.INVALID_POST.getMessage());
	}
}
