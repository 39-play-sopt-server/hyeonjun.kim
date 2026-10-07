package org.sopt;

import java.util.List;

import org.sopt.client.InputReader;
import org.sopt.client.OutputView;
import org.sopt.controller.PostController;
import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.repository.PostRepository;
import org.sopt.service.PostService;

public class Main {

	public static void main(String[] args) {
		InputReader inputReader = new InputReader();
		OutputView outputView = new OutputView();

		PostRepository repository = new PostRepository();
		PostService service = new PostService(repository);
		PostController controller = new PostController(service);

		while (true) {
			outputView.showMenu();

			try {
				int command = inputReader.readInt();

				switch (command) {
					case 1 -> {
						outputView.showCategory();
						int categoryNumber = inputReader.readInt();

						Category category = switch (categoryNumber) {
							case 1 -> Category.NOTICE;
							case 2 -> Category.FREE;
							case 3 -> Category.QUESTION;
							default -> throw new IllegalArgumentException("Invalid category number");
						};

						outputView.showTitlePrompt();
						String title = inputReader.readString();

						outputView.showContentPrompt();
						String content = inputReader.readString();

						controller.createPost(title, content, category);
						outputView.showCreatePostPrompt();
					}
					case 2 -> {
						List<Post> posts = controller.readPosts();
						outputView.showPosts(posts);
					}
					case 3 -> {
						outputView.showIdPrompt();
						long id = inputReader.readLong();

						Post post = controller.readPost(id);

						outputView.showPost(post);
					}
					case 4 -> {
						outputView.showIdPrompt();
						long id = inputReader.readLong();

						controller.readPost(id);

						outputView.showTitlePrompt();
						String title = inputReader.readString();

						outputView.showContentPrompt();
						String content = inputReader.readString();

						controller.updatePost(id, title, content);
					}
					case 5 -> {
						outputView.showIdPrompt();
						long id = inputReader.readLong();

						controller.deletePost(id);
					}
					case 6 -> {
						outputView.showExitPrompt();
						return;
					}
					default -> outputView.showInvalidInputPrompt();
				}
			} catch (IllegalArgumentException e) {
				outputView.showErrorMessage(e);
			}
		}
	}
}
