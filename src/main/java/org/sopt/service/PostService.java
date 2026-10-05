package org.sopt.service;

import java.util.List;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.repository.PostRepository;

public class PostService {
	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public void createPost(String title, String content, Category category) {
		if (title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be empty");
		}
		if (content.isBlank()) {
			throw new IllegalArgumentException("Content cannot be empty");
		}

		postRepository.create(title, content, category);
	}

	public List<Post> readPosts() {
		return postRepository.getPosts();
	}

	public Post readPost(long id) {
		return postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Post with id " + id + " does not exist"));
	}

	public void updatePost(long id, String title, String content) {
		if (title.isBlank()) {
			throw new IllegalArgumentException("Title cannot be empty");
		}
		if (content.isBlank()) {
			throw new IllegalArgumentException("Content cannot be empty");
		}
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Post with id " + id + " does not exist"));
		post.setTitle(title);
		post.setContent(content);
	}

	public void deletePost(long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Post with id " + id + " does not exist"));
		postRepository.deletePost(post);
	}
}
