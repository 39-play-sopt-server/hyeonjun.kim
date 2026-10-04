package org.sopt;

import org.sopt.controller.PostController;
import org.sopt.repository.PostRepository;
import org.sopt.service.PostService;

public class Main {

	public static void main(String[] args) {
		PostRepository repository = new PostRepository();
		PostService service = new PostService(repository);
		PostController controller = new PostController(service);

		controller.run();
	}
}
