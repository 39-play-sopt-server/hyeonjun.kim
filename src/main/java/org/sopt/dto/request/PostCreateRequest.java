package org.sopt.dto.request;

public record PostCreateRequest(
	String title,
	String content,
	int categoryNumber
) {
}
