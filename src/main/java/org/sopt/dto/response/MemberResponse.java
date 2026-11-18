package org.sopt.dto.response;

import org.sopt.entity.Member;
import org.sopt.entity.Role;

public record MemberResponse(
	Long memberId,
	String name,
	String email,
	Role role
) {
	public static MemberResponse from(Member member) {
		return new MemberResponse(member.getId(), member.getName(), member.getEmail(), member.getRole());
	}
}
