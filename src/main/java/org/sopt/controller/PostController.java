package org.sopt.controller;

import java.util.List;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.service.PostService;

public class PostController {
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	public void createPost(String title, String content, Category category) {
		postService.createPost(title, content, category);
	}

	public List<Post> readPosts() {
		return postService.readPosts();
	}

	public Post readPost(long id) {
		return postService.readPost(id);
	}

	public void updatePost(long id, String title, String content) {
		postService.updatePost(id, title, content);
	}

	public void deletePost(long id) {
		postService.deletePost(id);
	}
}

