package org.sopt.service;

import org.sopt.dto.response.MemberCreateResponse;
import org.sopt.entity.Member;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.PostLikeRepository;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;

	public MemberService(
		MemberRepository memberRepository,
		PostRepository postRepository,
		PostLikeRepository postLikeRepository
		) {
		this.memberRepository = memberRepository;
		this.postRepository = postRepository;
		this.postLikeRepository = postLikeRepository;
	}

	public MemberCreateResponse createMember(String name) {
		Member member = new Member(name);
		memberRepository.save(member);
		return new MemberCreateResponse(member.getId());
	}

	@Transactional
	public void deleteMember(Long id) {
		Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

		postLikeRepository.deleteByMemberId(id);
		postLikeRepository.deleteByPostMemberId(id);
		postRepository.deleteByMemberId(id);
		memberRepository.delete(member);
	}
}
