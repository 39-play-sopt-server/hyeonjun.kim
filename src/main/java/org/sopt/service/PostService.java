package org.sopt.service;

import java.util.List;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.exception.InvalidPostException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public void createPost(String title, String content, Category category) {
		if (title == null || title.isBlank() || content == null || content.isBlank()) {
			throw new InvalidPostException();
		}

		Post post = new Post(title, content, category);

		postRepository.save(post);
	}

	public List<Post> readPosts() {
		return postRepository.findAll();
	}

	public Post readPost(long id) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		post.setViewCount(post.getViewCount() + 1);
		postRepository.save(post);
		return post;
	}

	public void updatePost(long id, String title, String content) {
		if (title == null || title.isBlank() || content == null || content.isBlank()) {
			throw new InvalidPostException();
		}
		Post post = postRepository.findById(id)
			.orElseThrow(PostNotFoundException::new);

		post.setTitle(title);
		post.setContent(content);

		postRepository.save(post);
	}

	public void deletePost(long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(PostNotFoundException::new);
		postRepository.delete(post);
	}
}
