package org.sopt.service;

import java.util.List;

import org.sopt.dto.response.MemberCreateResponse;
import org.sopt.dto.response.MemberResponse;
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

	@Transactional
	public void deleteMember(Long id) {
		Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

		postLikeRepository.deleteByMemberId(id);
		postLikeRepository.deleteByPostMemberId(id);
		postRepository.deleteByMemberId(id);
		memberRepository.delete(member);
	}

	@Transactional
	public List<MemberResponse> getMembers() {
		List<Member> members = memberRepository.findAll();
		return members.stream()
			.map(MemberResponse::from)
			.toList();
	}
}
