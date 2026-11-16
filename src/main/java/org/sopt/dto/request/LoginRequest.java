package org.sopt.dto.request;

public record LoginRequest(
	String email,
	String password) {}
