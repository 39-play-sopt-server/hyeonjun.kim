package org.sopt.service;

import org.sopt.entity.Member;
import org.sopt.entity.Post;
import org.sopt.entity.PostLike;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.exception.PostLikeAlreadyExistException;
import org.sopt.exception.PostLikeNotFoundException;
import org.sopt.exception.PostNotFoundException;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.PostLikeRepository;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {
	private final PostLikeRepository postLikeRepository;
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;

	public PostLikeService(
		PostLikeRepository postLikeRepository,
		PostRepository postRepository,
		MemberRepository memberRepository) {
		this.postLikeRepository = postLikeRepository;
		this.postRepository = postRepository;
		this.memberRepository = memberRepository;
	}

	public void addLike(long memberId, long postId) {
		if (postLikeRepository.existsByMemberIdAndPostId(memberId, postId)) {
			throw new PostLikeAlreadyExistException();
		}
		Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
		Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

		postLikeRepository.save(new PostLike(member, post));
	}

	public void deleteLike(long memberId, long postId) {
		PostLike postLike = postLikeRepository.findByMemberIdAndPostId(memberId, postId).orElseThrow(PostLikeNotFoundException::new);
		postLikeRepository.delete(postLike);
	}
}
