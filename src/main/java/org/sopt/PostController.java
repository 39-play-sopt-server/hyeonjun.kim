package org.sopt;

import java.util.ArrayList;
import java.util.List;

public class PostController {
	private final PostView view;
	private final PostModel model;

	public PostController(PostView view, PostModel model) {
		this.view = view;
		this.model = model;
	}

	public void run() {
		while (true) {
			view.showMenu();

			int command = view.readCommand();

			switch (command) {
				case 1 -> createPost();
				case 2 -> readPosts();
				case 3 -> readPost();
				case 4 -> updatePost();
				case 5 -> deletePost();
				case 6 -> {
					view.showMessage("프로그램을 종료합니다.");
					return;
				}
				default -> view.showMessage("잘못된 입력입니다.");
			}
		}
	}
	private void createPost() {
		// 게시글 작성
		view.showMessage("제목: ");
		String title = view.readContent();

		view.showMessage("내용: ");
		String content = view.readContent();

		model.create(title, content);

		view.showMessage("게시글이 작성되었습니다.");
	}

	private void readPosts() {
		// 게시글 목록 조회
		view.showMessage("\n=== 게시글 목록 ===");

		if (model.getPosts().isEmpty()) {
			view.showMessage("게시글이 없습니다.");
			return;
		}

		for (int i = 0; i < model.getPosts().size(); i++) {
			Post currentPost = model.getPosts().get(i);

			view.showMessage(
				(i + 1) + ". " + currentPost.getTitle()
			);
		}
	}

	private void readPost() {
		// 게시글 단건 조회
		if (model.getPosts().isEmpty()) {
			view.showMessage("게시글이 없습니다.");
			return;
		}

		view.showMessage("조회할 게시글 번호: ");
		int readIndex = Integer.parseInt(view.readContent()) - 1;

		if (readIndex < 0 || readIndex >= model.getPosts().size()) {
			view.showMessage("존재하지 않는 게시글입니다.");
			return;
		}

		Post readPost = model.getPost(readIndex);

		view.showMessage("\n=== 게시글 ===");
		view.showMessage("제목: " + readPost.getTitle());
		view.showMessage("내용: " + readPost.getContent());
	}

	private void updatePost() {
		// 게시글 수정
		if (model.getPosts().isEmpty()) {
			view.showMessage("게시글이 없습니다.");
			return;
		}

		view.showMessage("수정할 게시글 번호: ");
		int updateIndex = Integer.parseInt(view.readContent()) - 1;

		if (updateIndex < 0 || updateIndex >= model.getPosts().size()) {
			view.showMessage("존재하지 않는 게시글입니다.");
			return;
		}

		Post updatePost = model.getPost(updateIndex);

		view.showMessage("새로운 제목: ");
		String newTitle = view.readContent();

		view.showMessage("새로운 내용: ");
		String newContent = view.readContent();

		updatePost.setTitle(newTitle);
		updatePost.setContent(newContent);

		view.showMessage("게시글이 수정되었습니다.");
	}

	private void deletePost() {
		// 게시글 삭제
		if (model.getPosts().isEmpty()) {
			view.showMessage("게시글이 없습니다.");
			return;
		}

		view.showMessage("삭제할 게시글 번호: ");
		int deleteIndex = Integer.parseInt(view.readContent()) - 1;

		if (deleteIndex < 0 || deleteIndex >= model.getPosts().size()) {
			view.showMessage("존재하지 않는 게시글입니다.");
			return;
		}

		model.deletePost(deleteIndex);

		view.showMessage("게시글이 삭제되었습니다.");
	}
}

