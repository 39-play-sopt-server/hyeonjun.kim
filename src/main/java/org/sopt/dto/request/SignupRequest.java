package org.sopt.dto.request;

public record SignupRequest(
	String name,
	String email,
	String password) {}
