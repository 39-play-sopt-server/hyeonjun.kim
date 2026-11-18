package org.sopt.dto.request;

import org.sopt.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignupRequest(
	@NotBlank @Size(max = 30) String name,
	@NotBlank @Email @Size(max = 255) String email,
	@NotBlank String password,
	@NotNull Role role) {}
