package org.sopt;

import java.util.Scanner;

public class PostView {
	private final Scanner scanner = new Scanner(System.in);

	public int readCommand() {
		return Integer.parseInt(scanner.nextLine());
	}

	public String readContent() {
		return scanner.nextLine();
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

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
}
