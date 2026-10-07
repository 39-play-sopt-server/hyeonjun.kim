package org.sopt.service;

import java.util.List;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.exception.InvalidPostException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.repository.PostRepository;

public class PostService {
	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public void createPost(String title, String content, Category category) {
		if (title.isBlank() || content.isBlank()) {
			throw new InvalidPostException();
		}
		postRepository.create(title, content, category);
	}

	public List<Post> readPosts() {
		return postRepository.getPosts();
	}

	public Post readPost(long id) {
		return postRepository.findById(id)
			.orElseThrow(PostNotFoundException::new);
	}

	public void updatePost(long id, String title, String content) {
		if (title.isBlank() || content.isBlank()) {
			throw new InvalidPostException();
		}
		Post post = postRepository.findById(id)
			.orElseThrow(PostNotFoundException::new);
		post.setTitle(title);
		post.setContent(content);
	}

	public void deletePost(long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(PostNotFoundException::new);
		postRepository.deletePost(post);
	}
}
