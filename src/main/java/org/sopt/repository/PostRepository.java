package org.sopt.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.sopt.entity.Category;
import org.sopt.entity.Post;

public class PostRepository {

	private final Map<Long, Post> posts = new HashMap<>();

	private long nextId = 1;

	public void create(String title, String content, Category category) {
		Post post = new Post(nextId++, title, content, category);
		posts.put(post.getId(), post);
	}

	public List<Post> getPosts() {
		return new ArrayList<>(posts.values());
	}

	public Optional<Post> findById(long id) {
		return Optional.ofNullable(posts.get(id));
	}

	public void deletePost(Post post) {
		posts.remove(post.getId());
	}
}
