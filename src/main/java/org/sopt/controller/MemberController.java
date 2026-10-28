package org.sopt.controller;

import org.sopt.dto.request.MemberCreateRequest;
import org.sopt.response.ApiResponse;
import org.sopt.response.SuccessCode;
import org.sopt.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="api/v1")
public class MemberController {
	private final MemberService memberService;

	public MemberController(MemberService memberService) {this.memberService = memberService;}

	@PostMapping(path="/member")
	public ResponseEntity<ApiResponse<Long>> createMember(
		@Valid @RequestBody MemberCreateRequest request
	) {
		long id = memberService.createMember(request.name());
		ApiResponse<Long> response = ApiResponse.success(SuccessCode.MEMBER_CREATED, id);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}

	@DeleteMapping(path="/member/{memberId}")
	public ResponseEntity<ApiResponse<Void>> deleteMember(
		@PathVariable Long memberId
	) {
		memberService.deleteMember(memberId);
		ApiResponse<Void> response = ApiResponse.success(SuccessCode.MEMBER_DELETED);
		return ResponseEntity
			.status(response.getStatus())
			.body(response);
	}
}
