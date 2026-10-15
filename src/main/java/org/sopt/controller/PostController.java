package org.sopt.controller;

import java.util.List;
import java.util.Map;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.sopt.response.ApiResponse;
import org.sopt.response.SuccessCode;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
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
public class PostController implements PostControllerDocs{
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping(path="post")
	public ResponseEntity<ApiResponse<Void>> createPost(
		@RequestBody Map<String, Object> body) {
		String title = (String) body.get("title");
		String content = (String) body.get("content");
		int categoryNumber = (Integer) body.get("categoryNumber");

		Category category = Category.fromNumber(categoryNumber);

		postService.createPost(title, content, category);
		ApiResponse<Void> response = ApiResponse.success(SuccessCode.POST_CREATED);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@GetMapping(path="posts")
	public ResponseEntity<ApiResponse<List<Post>>> readPosts() {
		List<Post> posts = postService.readPosts();
		ApiResponse<List<Post>> response = ApiResponse.success(SuccessCode.POST_LIST_FOUND, posts);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@GetMapping(path="post/{postId}")
	public ResponseEntity<ApiResponse<Post>> readPost(
		@PathVariable(name="postId") long id) {

		Post post = postService.readPost(id);
		ApiResponse<Post> response = ApiResponse.success(SuccessCode.POST_FOUND, post);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@PatchMapping(path="post/{postId}")
	public ResponseEntity<ApiResponse<Void>> updatePost(
		@PathVariable(name="postId") long id,
		@RequestBody Map<String, Object> body) {
		String title = (String) body.get("title");
		String content = (String) body.get("content");

		postService.updatePost(id, title, content);
		ApiResponse<Void> response = ApiResponse.success(SuccessCode.POST_UPDATED);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@DeleteMapping(path="post/{postId}")
	public ResponseEntity<ApiResponse<Void>> deletePost(
		@PathVariable(name="postId") long id) {
		postService.deletePost(id);
		ApiResponse<Void> response = ApiResponse.success(SuccessCode.POST_DELETED);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}
}

