package org.sopt.controller;

import java.util.List;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.exception.InvalidCategoryException;
import org.sopt.exception.InvalidPostException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.response.ApiResponse;
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
			return ApiResponse.success(200, "POST_CREATED", "게시물 생성에 성공했습니다.");
		} catch (InvalidPostException e) {
			return ApiResponse.failure(400, "INVALID_POST", e.getMessage());
		} catch (InvalidCategoryException e) {
			return ApiResponse.failure(400, "INVALID_CATEGORY", e.getMessage());
		}
	}

	public ApiResponse<List<Post>> readPosts() {
		List<Post> posts = postService.readPosts();
		return ApiResponse.success(200, "POST_LIST_FOUND", "게시글 목록 조회에 성공했습니다.", posts);
	}

	public ApiResponse<Post> readPost(long id) {
		try {
			Post post = postService.readPost(id);
			return ApiResponse.success(200, "POST_FOUND", "게시글 조회에 성공했습니다.", post);
		} catch (PostNotFoundException e) {
			return ApiResponse.failure(404, "POST_NOT_FOUND", e.getMessage());
		}
	}

	public ApiResponse<Void> updatePost(long id, String title, String content) {
		try {
			postService.updatePost(id, title, content);
			return ApiResponse.success(200, "POST_UPDATED", "게시글 수정에 성공했습니다.");
		} catch (PostNotFoundException e) {
			return ApiResponse.failure(404, "POST_NOT_FOUND", e.getMessage());
		} catch (InvalidPostException e) {
			return ApiResponse.failure(400, "INVALID_POST", e.getMessage());
		}
	}

	public ApiResponse<Void> deletePost(long id) {
		try {
			postService.deletePost(id);
			return ApiResponse.success(200, "POST_DELETED", "게시글 삭제에 성공했습니다.");
		} catch (PostNotFoundException e) {
			return ApiResponse.failure(404, "POST_NOT_FOUND", e.getMessage());
		}
	}
}

