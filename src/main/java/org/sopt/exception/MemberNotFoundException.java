package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class MemberNotFoundException extends RuntimeException {
	public MemberNotFoundException() {super(ErrorCode.MEMBER_NOT_FOUND.getMessage());}
}
