package org.sopt.controller;

import java.util.List;
import java.util.Scanner;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.service.PostService;

public class PostController {
	private final Scanner scanner = new Scanner(System.in);
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	public void run() {
		while (true) {
			showMenu();

			try {
				int command = Integer.parseInt(scanner.nextLine());

				switch (command) {
					case 1 -> {
						System.out.println("Enter category number (1. NOTICE, 2. FREE, 3. QUESTION)");
						int categoryNumber = Integer.parseInt(scanner.nextLine());

						Category category = switch (categoryNumber) {
							case 1 -> Category.NOTICE;
							case 2 -> Category.FREE;
							case 3 -> Category.QUESTION;
							default -> throw new IllegalArgumentException("Invalid category number");
						};

						System.out.println("Enter title:");
						String title = scanner.nextLine();

						System.out.println("Enter content:");
						String content = scanner.nextLine();

						postService.createPost(title, content, category);
						System.out.println("Post created");
					}
					case 2 -> {
						List<Post> posts = postService.readPosts();

						if (posts.isEmpty()) {
							System.out.println("No posts found");
						} else {
							System.out.println("Posts found");
							for (Post post : posts) {
								System.out.println(post.getId() + ". " + post.getTitle());
							}
						}
					}
					case 3 -> {
						System.out.println("Enter id:");
						long id = Long.parseLong(scanner.nextLine());

						Post post = postService.readPost(id);

						System.out.println("title: " + post.getTitle());
						System.out.println("content: " + post.getContent());
						System.out.println("category: " + post.getCategory());
					}
					case 4 -> {
						System.out.println("Enter id:");
						long id = Long.parseLong(scanner.nextLine());

						postService.readPost(id);

						System.out.println("Enter title:");
						String title = scanner.nextLine();

						System.out.println("Enter content:");
						String content = scanner.nextLine();

						postService.updatePost(id, title, content);
					}
					case 5 -> {
						System.out.println("Enter id:");
						long id = Long.parseLong(scanner.nextLine());

						postService.deletePost(id);
					}
					case 6 -> {
						System.out.println("프로그램을 종료합니다.");
						return;
					}
					default -> System.out.println("잘못된 입력입니다.");
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	private void showMenu() {
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

