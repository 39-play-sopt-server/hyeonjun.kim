package org.sopt.dto.response;

import org.sopt.entity.Post;

public record PostsReadResponse(
	Long postId,
	String title
) {
	public static PostsReadResponse from(Post post) {
		return new PostsReadResponse(
			post.getId(),
			post.getTitle()
		);
	}
}
