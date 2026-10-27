package org.sopt.controller;

import org.sopt.response.ApiResponse;
import org.sopt.response.SuccessCode;
import org.sopt.service.PostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1")
public class PostLikeController {
	private final PostLikeService postLikeService;

	public PostLikeController(PostLikeService postLikeService) {
		this.postLikeService = postLikeService;
	}

	@PostMapping(path="/postLike")
	public ResponseEntity<ApiResponse<Void>> addLike(
		@RequestParam long memberId,
		@RequestParam long postId
	) {
		postLikeService.addLike(memberId, postId);
		ApiResponse<Void> response = ApiResponse.success(SuccessCode.POSTLIKE_CREATED, null);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@DeleteMapping(path="/postLike")
	public ResponseEntity<ApiResponse<Void>> deleteLike(
		@RequestParam long memberId,
		@RequestParam long postId
	) {
		postLikeService.deleteLike(memberId, postId);
		ApiResponse<Void> response = ApiResponse.success(SuccessCode.POSTLIKE_DELETED, null);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}
}
