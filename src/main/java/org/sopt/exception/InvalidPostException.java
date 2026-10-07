package org.sopt.exception;

public class InvalidPostException extends RuntimeException {
	public InvalidPostException() {
		super("제목/내용은 비어있을 수 없습니다.");
	}
}
