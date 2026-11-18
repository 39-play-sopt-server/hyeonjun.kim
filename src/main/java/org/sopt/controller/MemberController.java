package org.sopt.controller;

import java.time.Duration;

import org.sopt.response.ApiResponse;
import org.sopt.response.ErrorCode;
import org.sopt.response.SuccessCode;
import org.sopt.service.AuthService;
import org.sopt.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path="api/v1")
public class MemberController {
	private final MemberService memberService;
	private final AuthService authService;

	public MemberController(MemberService memberService, AuthService authService) {
		this.memberService = memberService;
		this.authService = authService;
	}

	@GetMapping("/me")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Long> me(@AuthenticationPrincipal Long memberId) {
		return ResponseEntity.ok(memberId);
	}
}
