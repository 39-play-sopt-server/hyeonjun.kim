package org.sopt.service;

import java.util.List;

import org.sopt.entity.Post;
import org.sopt.repository.PostRepository;

public class PostService {
	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public void createPost(String title, String content) {
		postRepository.create(title, content);
	}

	public List<Post> readPosts() {
		return postRepository.getPosts();
	}

	public Post readPost(int id) {
		if (id < 0 || id >= postRepository.getPosts().size()) {
			throw new IllegalArgumentException("Invalid post id " + id);
		}
		return postRepository.getPost(id);
	}

	public void updatePost(int id, String title, String content) {
		if (id < 0 || id >= postRepository.getPosts().size()) {
			throw new IllegalArgumentException("Invalid post id " + id);
		}
		Post post = postRepository.getPost(id);
		post.setTitle(title);
		post.setContent(content);
	}

	public void deletePost(int id) {
		if (id < 0 || id >= postRepository.getPosts().size()) {
			throw new IllegalArgumentException("Invalid post id " + id);
		}
		postRepository.deletePost(id);
	}
}
