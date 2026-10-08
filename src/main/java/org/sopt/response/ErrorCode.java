package org.sopt.response;

public enum ErrorCode {
	INVALID_POST(400, "제목/내용은 비어있을 수 없습니다."),
	INVALID_CATEGORY(400, "카테고리는 1~3 중 선택해야합니다."),
	POST_NOT_FOUND(404, "게시글이 존재하지 않습니다."),
	INVALID_INPUT(400, "잘못된 입력입니다.");

	private final int status;
	private final String message;

	ErrorCode(int status, String message) {
		this.status = status;
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
}
