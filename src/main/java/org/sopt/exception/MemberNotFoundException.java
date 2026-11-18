package org.sopt.exception;

import org.sopt.response.ErrorCode;

public class MemberNotFoundException extends BaseException {
	public MemberNotFoundException() {super(ErrorCode.MEMBER_NOT_FOUND);}
}
