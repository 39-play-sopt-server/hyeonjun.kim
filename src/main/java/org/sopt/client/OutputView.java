package org.sopt.client;

import org.sopt.response.ApiResponse;

public class OutputView {
	public void showMenu() {
		System.out.println("\n=== 게시판 ===");
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 목록 조회");
		System.out.println("3. 게시글 단건 조회");
		System.out.println("4. 게시글 수정");
		System.out.println("5. 게시글 삭제");
		System.out.println("6. 종료");
		System.out.print("선택: ");
	}

	public void showCategory() {
		System.out.println("Enter category number (1. NOTICE, 2. FREE, 3. QUESTION)");
	}

	public void showTitlePrompt() {
		System.out.println("Enter title:");
	}

	public void showContentPrompt() {
		System.out.println("Enter content:");
	}

	public void showIdPrompt() {
		System.out.println("Enter post id:");
	}

	public void showExitPrompt() {
		System.out.println("프로그램을 종료합니다.");
	}

	public void showInvalidInputPrompt() {
		System.out.println("잘못된 입력입니다.");
	}

	public void showErrorMessage(IllegalArgumentException e) {
		System.out.println(e.getMessage());
	}

	public void showResponse(ApiResponse<?> response) {
		System.out.println("status: " + response.getStatus());
		System.out.println("code: " + response.getCode());
		System.out.println("message: " + response.getMessage());
		System.out.println("data: " + response.getData());
	}
}
