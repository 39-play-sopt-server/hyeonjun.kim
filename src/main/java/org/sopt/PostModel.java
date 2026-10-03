package org.sopt;

import java.util.ArrayList;
import java.util.List;

public class PostModel {
	private final List<Post> posts = new ArrayList<>();

	public void create(String title, String content) {
		posts.add(new Post(title, content));
	}
	public List<Post> getPosts() {
		return posts;
	}

	public Post getPost(int id) {
		return posts.get(id);
	}

	public void deletePost(int id) {
		posts.remove(id);
	}
}
