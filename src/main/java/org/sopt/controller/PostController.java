package org.sopt.controller;

import org.sopt.dto.request.PostCreateRequest;
import org.sopt.dto.request.PostUpdateRequest;
import org.sopt.dto.response.PostReadResponse;
import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.response.ApiResponse;
import org.sopt.response.SuccessCode;
import org.sopt.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1")
public class PostController implements PostControllerDocs{
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping(path="/member/{memberId}/post")
	public ResponseEntity<ApiResponse<Void>> createPost(
		@RequestBody PostCreateRequest request,
		@PathVariable Long memberId) {

		Category category = Category.fromNumber(request.categoryNumber());

		postService.createPost(memberId, request.title(), request.content(), category);
		ApiResponse<Void> response = ApiResponse.success(SuccessCode.POST_CREATED);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@GetMapping(path="/posts")
	public ResponseEntity<ApiResponse<Page<Post>>> readPosts(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		Page<Post> posts = postService.readPosts(page, size);
		ApiResponse<Page<Post>> response = ApiResponse.success(SuccessCode.POST_LIST_FOUND, posts);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@GetMapping(path="/post/{postId}")
	public ResponseEntity<ApiResponse<PostReadResponse>> readPost(
		@PathVariable(name="postId") long id) {
		PostReadResponse postReadResponse = postService.readPost(id);
		ApiResponse<PostReadResponse> response = ApiResponse.success(SuccessCode.POST_FOUND, postReadResponse);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@PatchMapping(path="/member/{memberId}/post/{postId}")
	public ResponseEntity<ApiResponse<Void>> updatePost(
		@PathVariable(name="postId") long postId,
		@PathVariable(name="memberId") long memberId,
		@RequestBody PostUpdateRequest request) {

		postService.updatePost(memberId, postId, request.title(), request.content());
		ApiResponse<Void> response = ApiResponse.success(SuccessCode.POST_UPDATED);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@DeleteMapping(path="/member/{memberId}/post/{postId}")
	public ResponseEntity<ApiResponse<Void>> deletePost(
		@PathVariable(name="memberId") long memberId,
		@PathVariable(name="postId") long postId) {
		postService.deletePost(memberId, postId);
		ApiResponse<Void> response = ApiResponse.success(SuccessCode.POST_DELETED);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}
}

