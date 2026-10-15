package org.sopt.controller;

import java.util.List;
import java.util.Map;

import org.sopt.entity.Post;
import org.sopt.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Post", description = "Post API")
public interface PostControllerDocs {

	@Operation(summary = "게시물 생성", description = "title, content, categorynumber를 입력받아 게시물을 생성합니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "게시물 생성 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 입력입니다.")
	})
	ResponseEntity<ApiResponse<Void>> createPost(Map<String, Object> body);

	@Operation(summary = "게시글 목록 조회", description = "전체 게시글 목록을 조회합니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글 목록 조회에 성공했습니다.")
	})
	ResponseEntity<ApiResponse<List<Post>>> readPosts();

	@Operation(summary = "게시글 단건 조회", description = "id를 입력받아 게시글을 조회합니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글 단건 조회에 성공했습니다."),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "게시글이 존재하지 않습니다.")
	})
	ResponseEntity<ApiResponse<Post>> readPost(long id);

	@Operation(summary = "게시글 수정", description = "id, title, content를 입력받아 해당 게시글을 수정합니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글 수정에 성공했습니다."),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "게시글이 존재하지 않습니다"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 입력입니다.")
	})
	ResponseEntity<ApiResponse<Void>> updatePost(long id, Map<String, Object> body);

	@Operation(summary = "게시글 삭제", description = "id를 입력받아 해당 게시글을 삭제합니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "게시글 삭제에 성공했습니다."),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "게시글이 존재하지 않습니다.")
	})
	ResponseEntity<ApiResponse<Void>> deletePost(long id);
}
