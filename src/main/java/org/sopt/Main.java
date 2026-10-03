package org.sopt;

public class Main {

	public static void main(String[] args) {
		PostView view = new PostView();
		PostModel model = new PostModel();
		PostController controller = new PostController(view, model);
		controller.run();
	}
}
