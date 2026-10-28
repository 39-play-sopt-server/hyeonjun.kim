package org.sopt.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostCreateRequest(
	@NotBlank(message = "title은 필수입니다.")
	@Size(max=30, message = "제목은 30자 이하여야 합니다.")
	String title,

	@NotBlank(message = "content는 필수입니다.")
	@Size(max = 10000, message = "내용은 10,000자 이하여야 합니다.")
	String content,

	@NotNull(message = "categoryNumber는 필수입니다.")
	@Min(value = 1, message = "categoryNumber는 1 이상이어야 합니다.")
	@Max(value = 3, message = "categoryNumber는 3 이하여야 합니다.")
	int categoryNumber
) {
}
