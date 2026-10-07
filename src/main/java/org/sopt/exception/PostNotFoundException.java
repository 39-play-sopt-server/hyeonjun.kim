package org.sopt.exception;

public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException() {
		super("게시글이 존재하지 않습니다.");
	}
}
