package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberCreateRequest(
	@NotBlank(message = "name은 필수입니다.")
	@Size(max = 30, message = "이름은 30자 이하여야 합니다.")
	String name
) {
}
