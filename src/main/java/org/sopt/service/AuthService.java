package org.sopt.service;

import org.sopt.config.JwtProvider;
import org.sopt.dto.response.TokenResponse;
import org.sopt.entity.Member;
import org.sopt.entity.RefreshToken;
import org.sopt.entity.RefreshTokenHasher;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.RefreshTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

@Service
public class AuthService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final RefreshTokenRepository refreshTokenRepository;
	private final RefreshTokenHasher refreshTokenHasher;

	public AuthService(
		MemberRepository memberRepository,
		PasswordEncoder passwordEncoder,
		JwtProvider jwtProvider,
		RefreshTokenRepository refreshTokenRepository,
		RefreshTokenHasher refreshTokenHasher) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
		this.refreshTokenRepository = refreshTokenRepository;
		this.refreshTokenHasher = refreshTokenHasher;
	}

	@Transactional
	public Long signup(String name, String email, String password) {
		if (memberRepository.existsByEmail(email)) {
			throw new IllegalArgumentException("이미 가입된 이메일입니다.");
		}
		Member member = new Member(name, email, passwordEncoder.encode(password));
		return memberRepository.save(member).getId();
	}

	@Transactional
	public TokenResponse login(String email, String password) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalArgumentException("이메일이 올바르지 않습니다."));

		if (!passwordEncoder.matches(password, member.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
		}
		return issueTokens(member);
	}

	@Transactional
	public TokenResponse reissue(String refreshToken) {
		if (!jwtProvider.isRefreshToken(refreshToken)) {
			throw new JwtException("Refresh token이 아닙니다.");
		}
		Long memberId = jwtProvider.getMemberId(refreshToken);
		String tokenHash = refreshTokenHasher.hash(refreshToken);

		RefreshToken savedToken = refreshTokenRepository
			.findByMemberIdAndTokenHash(memberId, tokenHash)
			.orElseThrow(() ->
				new IllegalArgumentException("유효하지 않은 Refresh Token입니다.")
			);
		Member member = savedToken.getMember();

		return issueTokens(member);
	}

	private TokenResponse issueTokens(Member member) {
		String accessToken = jwtProvider.createAccessToken(member.getId());
		String refreshToken = jwtProvider.createRefreshToken(member.getId());

		String tokenHash = refreshTokenHasher.hash(refreshToken);

		RefreshToken token = refreshTokenRepository
			.findByMemberId(member.getId())
			.orElseGet(() -> new RefreshToken(member, tokenHash));

		token.updateTokenHash(tokenHash);
		refreshTokenRepository.save(token);

		return TokenResponse.of(accessToken, refreshToken);
	}
}
