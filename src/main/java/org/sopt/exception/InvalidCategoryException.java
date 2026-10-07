package org.sopt.exception;

public class InvalidCategoryException extends RuntimeException {
	public InvalidCategoryException() {
		super("카테고리는 1~3 중 선택해야합니다.");
	}
}
