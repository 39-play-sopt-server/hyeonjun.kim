package org.sopt.controller;

import java.util.List;

import org.sopt.dto.response.MemberResponse;
import org.sopt.response.ApiResponse;
import org.sopt.response.SuccessCode;
import org.sopt.service.MemberService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1")
public class MemberController {
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping(path = "/members")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse<List<MemberResponse>>> getMembers() {
		List<MemberResponse> memberResponse = memberService.getMembers();
		ApiResponse<List<MemberResponse>> response = ApiResponse.success(SuccessCode.MEMBER_LIST_FOUND, memberResponse);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}
}
