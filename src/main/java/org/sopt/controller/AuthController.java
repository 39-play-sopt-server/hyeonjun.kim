package org.sopt.controller;

import java.time.Duration;

import org.sopt.dto.request.LoginRequest;
import org.sopt.dto.request.SignupRequest;
import org.sopt.dto.response.SignUpResponse;
import org.sopt.dto.response.TokenResponse;
import org.sopt.response.ApiResponse;
import org.sopt.response.SuccessCode;
import org.sopt.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<SignUpResponse>> signup(@Valid @RequestBody SignupRequest request) {
		SignUpResponse response = authService.signup(request.name(), request.email(), request.password(), request.role());
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success(SuccessCode.MEMBER_CREATED, response));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Void>> login(@Valid @RequestBody LoginRequest request) {
		TokenResponse tokenResponse = authService.login(request.email(), request.password());
		return createTokenResponse(tokenResponse, SuccessCode.LOGIN_SUCCESS);
	}

	@PostMapping(path = "/reissue")
	public ResponseEntity<ApiResponse<Void>> reissue(
		@CookieValue("refreshToken") String refreshToken) {
		TokenResponse tokenResponse = authService.reissue(refreshToken);
		return createTokenResponse(tokenResponse, SuccessCode.REISSUE_SUCCESS);
	}

	@DeleteMapping(path = "/logout")
	public ResponseEntity<ApiResponse<Void>> logout(
		@AuthenticationPrincipal Long memberId
	) {
		authService.logout(memberId);
		return deleteTokenResponse(SuccessCode.LOGOUT_SUCCESS);
	}

	@DeleteMapping(path = "/withdraw")
	public ResponseEntity<ApiResponse<Void>> withdraw(
		@AuthenticationPrincipal Long memberId
	) {
		authService.withdraw(memberId);
		return deleteTokenResponse(SuccessCode.WITHDRAW_SUCCESS);
	}

	private ResponseEntity<ApiResponse<Void>> createTokenResponse(
		TokenResponse tokens,
		SuccessCode successCode) {
		ResponseCookie cookie = ResponseCookie
			.from("refreshToken", tokens.refreshToken())
			.httpOnly(true)
			.secure(true)
			.sameSite("Strict")
			.path("/api/v1/auth")
			.maxAge(Duration.ofDays(7))
			.build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + tokens.accessToken())
			.header(HttpHeaders.SET_COOKIE, cookie.toString())
			.body(ApiResponse.success(successCode));
	}

	private ResponseEntity<ApiResponse<Void>> deleteTokenResponse(SuccessCode successCode) {
		ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
			.httpOnly(true)
			.secure(true)
			.path("/")
			.maxAge(0)
			.build();

		return ResponseEntity
			.status(HttpStatus.OK)
			.header(HttpHeaders.SET_COOKIE, cookie.toString())
			.body(ApiResponse.success(successCode));
	}
}
