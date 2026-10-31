package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUpdateRequest(
	@NotBlank(message = "title은 필수입니다.")
	@Size(max=30, message = "제목은 30자 이하여야 합니다.")
	String title,

	@NotBlank(message = "content는 필수입니다.")
	@Size(max = 10000, message = "내용은 10,000자 이하여야 합니다.")
	String content
) {
}
