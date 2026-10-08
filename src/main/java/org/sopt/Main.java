package org.sopt;

import java.util.List;

import org.sopt.client.InputReader;
import org.sopt.client.OutputView;
import org.sopt.controller.PostController;
import org.sopt.entity.Post;
import org.sopt.repository.PostRepository;
import org.sopt.response.ApiResponse;
import org.sopt.response.ErrorCode;
import org.sopt.response.SuccessCode;
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

						outputView.showTitlePrompt();
						String title = inputReader.readString();

						outputView.showContentPrompt();
						String content = inputReader.readString();

						ApiResponse<Void> response = controller.createPost(title, content, categoryNumber);
						outputView.showResponse(response);
					}
					case 2 -> {
						ApiResponse<List<Post>> response = controller.readPosts();
						outputView.showResponse(response);
					}
					case 3 -> {
						outputView.showIdPrompt();
						long id = inputReader.readLong();

						ApiResponse<Post> response = controller.readPost(id);
						outputView.showResponse(response);
					}
					case 4 -> {
						outputView.showIdPrompt();
						long id = inputReader.readLong();

						outputView.showTitlePrompt();
						String title = inputReader.readString();

						outputView.showContentPrompt();
						String content = inputReader.readString();

						ApiResponse<Void> response = controller.updatePost(id, title, content);
						outputView.showResponse(response);
					}
					case 5 -> {
						outputView.showIdPrompt();
						long id = inputReader.readLong();

						ApiResponse<Void> response = controller.deletePost(id);
						outputView.showResponse(response);
					}
					case 6 -> {
						ApiResponse<Void> response = ApiResponse.success(SuccessCode.EXIT);
						outputView.showResponse(response);
						return;
					}
					default -> {
						ApiResponse<Void> response = ApiResponse.failure(ErrorCode.INVALID_INPUT);
						outputView.showResponse(response);
					}
				}
			} catch (IllegalArgumentException e) {
				ApiResponse<Void> response = ApiResponse.failure(ErrorCode.INVALID_INPUT);
				outputView.showResponse(response);
			}
		}
	}
}
