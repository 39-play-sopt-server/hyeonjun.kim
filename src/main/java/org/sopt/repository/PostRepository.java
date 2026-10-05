package org.sopt.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sopt.entity.Category;
import org.sopt.entity.Post;

public class PostRepository {

	private final List<Post> posts = new ArrayList<>();

	private long nextId = 1;

	public void create(String title, String content, Category category) {
		posts.add(new Post(nextId++, title, content, category));
	}
	public List<Post> getPosts() {
		return posts;
	}

	public Optional<Post> findById(long id) {
		return posts.stream()
			.filter(post -> post.getId() == id)
			.findFirst();
	}

	public void deletePost(Post post) {
		posts.remove(post);
	}
}
