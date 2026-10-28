package org.sopt.service;

import org.sopt.dto.response.PostReadResponse;
import org.sopt.dto.response.PostsReadResponse;
import org.sopt.entity.Category;
import org.sopt.entity.Member;
import org.sopt.entity.Post;
import org.sopt.exception.InvalidPostException;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.exception.PostNotOwnedException;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.PostLikeRepository;
import org.sopt.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final PostLikeRepository postLikeRepository;

	public PostService(PostRepository postRepository, MemberRepository memberRepository, PostLikeRepository postLikeRepository) {
		this.postRepository = postRepository;
		this.memberRepository = memberRepository;
		this.postLikeRepository = postLikeRepository;
	}

	public void createPost(Long memberId, String title, String content, Category category) {
		Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
		Post post = new Post(member, title, content, category);
		postRepository.save(post);
	}

	public Page<PostsReadResponse> readPosts(int page, int size, Long memberId, Category category, String title) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		return postRepository.findBySearchOption(pageable, memberId, category, title).map(PostsReadResponse::from);
	}

	public PostReadResponse readPost(long id) {
		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		String name = post.getMember().getName();
		int likeCount = postLikeRepository.countByPostId(id);
		post.setViewCount(post.getViewCount() + 1);
		postRepository.save(post);
		return new PostReadResponse(
			post.getId(),
			post.getTitle(),
			name,
			post.getCategory().name(),
			post.getContent(),
			post.getViewCount(),
			likeCount);
	}

	public void updatePost(Long memberId, Long postId, String title, String content) {
		Post post = postRepository.findById(postId)
			.orElseThrow(PostNotFoundException::new);

		if (!post.getMember().getId().equals(memberId)) {
			throw new PostNotOwnedException();
		}

		post.setTitle(title);
		post.setContent(content);

		postRepository.save(post);
	}

	public void deletePost(long memberId, long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(PostNotFoundException::new);

		if (!post.getMember().getId().equals(memberId)) {
			throw new PostNotOwnedException();
		}

		postRepository.delete(post);
	}
}
