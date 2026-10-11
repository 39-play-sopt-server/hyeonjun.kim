package org.sopt.controller;

import java.util.List;
import java.util.Map;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.exception.InvalidCategoryException;
import org.sopt.exception.InvalidPostException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.response.ApiResponse;
import org.sopt.response.ErrorCode;
import org.sopt.response.SuccessCode;
import org.sopt.service.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1")
public class PostController {
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping(path="post")
	public ApiResponse<Void> createPost(
		@RequestBody Map<String, Object> body) {
		String title = (String) body.get("title");
		String content = (String) body.get("content");
		int categoryNumber = (Integer) body.get("categoryNumber");

		try {
			Category category = Category.fromNumber(categoryNumber);

			postService.createPost(title, content, category);
			return ApiResponse.success(SuccessCode.POST_CREATED);
		} catch (InvalidPostException e) {
			return ApiResponse.failure(ErrorCode.INVALID_POST);
		} catch (InvalidCategoryException e) {
			return ApiResponse.failure(ErrorCode.INVALID_CATEGORY);
		}
	}

	@GetMapping(path="posts")
	public ApiResponse<List<Post>> readPosts() {
		List<Post> posts = postService.readPosts();
		return ApiResponse.success(SuccessCode.POST_LIST_FOUND, posts);
	}

	@GetMapping(path="post/{postId}")
	public ApiResponse<Post> readPost(
		@PathVariable(name="postId") long id) {
		try {
			Post post = postService.readPost(id);
			return ApiResponse.success(SuccessCode.POST_FOUND, post);
		} catch (PostNotFoundException e) {
			return ApiResponse.failure(ErrorCode.POST_NOT_FOUND);
		}
	}

	@PatchMapping(path="post/{postId}")
	public ApiResponse<Void> updatePost(
		@PathVariable(name="postId") long id,
		@RequestBody Map<String, Object> body) {
		String title = (String) body.get("title");
		String content = (String) body.get("content");

		try {
			postService.updatePost(id, title, content);
			return ApiResponse.success(SuccessCode.POST_UPDATED);
		} catch (PostNotFoundException e) {
			return ApiResponse.failure(ErrorCode.POST_NOT_FOUND);
		} catch (InvalidPostException e) {
			return ApiResponse.failure(ErrorCode.INVALID_POST);
		}
	}

	@DeleteMapping(path="post/{postId}")
	public ApiResponse<Void> deletePost(
		@PathVariable(name="postId") long id) {
		try {
			postService.deletePost(id);
			return ApiResponse.success(SuccessCode.POST_DELETED);
		} catch (PostNotFoundException e) {
			return ApiResponse.failure(ErrorCode.POST_NOT_FOUND);
		}
	}
}

