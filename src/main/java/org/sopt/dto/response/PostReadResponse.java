package org.sopt.dto.response;

import org.sopt.entity.Post;

public record PostReadResponse(
	Long postId,
	String title,
	String author,
	String category,
	String content,
	Long viewCount,
	int likeCount
) {}
