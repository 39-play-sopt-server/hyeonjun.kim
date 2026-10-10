package org.sopt.controller;

import java.util.List;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.exception.InvalidCategoryException;
import org.sopt.exception.InvalidPostException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.response.ApiResponse;
import org.sopt.response.ErrorCode;
import org.sopt.response.SuccessCode;
import org.sopt.service.PostService;

public class PostController {
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	public ApiResponse<Void> createPost(String title, String content, int categoryNumber) {
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

	public ApiResponse<List<Post>> readPosts() {
		List<Post> posts = postService.readPosts();
		return ApiResponse.success(SuccessCode.POST_LIST_FOUND, posts);
	}

	public ApiResponse<Post> readPost(long id) {
		try {
			Post post = postService.readPost(id);
			return ApiResponse.success(SuccessCode.POST_FOUND, post);
		} catch (PostNotFoundException e) {
			return ApiResponse.failure(ErrorCode.POST_NOT_FOUND);
		}
	}

	public ApiResponse<Void> updatePost(long id, String title, String content) {
		try {
			postService.updatePost(id, title, content);
			return ApiResponse.success(SuccessCode.POST_UPDATED);
		} catch (PostNotFoundException e) {
			return ApiResponse.failure(ErrorCode.POST_NOT_FOUND);
		} catch (InvalidPostException e) {
			return ApiResponse.failure(ErrorCode.INVALID_POST);
		}
	}

	public ApiResponse<Void> deletePost(long id) {
		try {
			postService.deletePost(id);
			return ApiResponse.success(SuccessCode.POST_DELETED);
		} catch (PostNotFoundException e) {
			return ApiResponse.failure(ErrorCode.POST_NOT_FOUND);
		}
	}
}

