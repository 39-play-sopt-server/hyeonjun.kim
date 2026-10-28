package org.sopt.service;

import org.sopt.entity.Member;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {this.memberRepository = memberRepository;}

	public long createMember(String name) {
		Member member = new Member(name);
		memberRepository.save(member);
		return member.getId();
	}

	public void deleteMember(Long id) {
		Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
		memberRepository.delete(member);
	}
}
