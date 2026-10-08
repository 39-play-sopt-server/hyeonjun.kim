package org.sopt.response;

public enum SuccessCode {
	POST_CREATED(200, "게시글 생성에 성공했습니다."),
	POST_LIST_FOUND(200, "게시글 목록 조회에 성공했습니다."),
	POST_FOUND(200, "게시글 조회에 성공했습니다."),
	POST_UPDATED(200, "게시글 수정에 성공했습니다."),
	POST_DELETED(200, "게시글 삭제에 성공했습니다."),
	EXIT(200, "종료");

	private final int status;
	private final String message;

	SuccessCode(int status, String message) {
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
