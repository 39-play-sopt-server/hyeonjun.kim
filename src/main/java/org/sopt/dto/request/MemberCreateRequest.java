package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberCreateRequest(
	@NotBlank(message = "name은 필수입니다.")
	String name
) {
}
