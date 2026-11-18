package org.sopt.service;

import java.util.UUID;

import org.sopt.config.JwtProvider;
import org.sopt.dto.response.SignUpResponse;
import org.sopt.dto.response.TokenResponse;
import org.sopt.entity.Member;
import org.sopt.entity.RefreshToken;
import org.sopt.entity.RefreshTokenHasher;
import org.sopt.entity.Role;
import org.sopt.exception.EmailAlreadyExistsException;
import org.sopt.exception.InvalidCredentialException;
import org.sopt.exception.InvalidRefreshTokenException;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.RefreshTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.JwtException;

@Service
public class AuthService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final RefreshTokenRepository refreshTokenRepository;
	private final RefreshTokenHasher refreshTokenHasher;
	private final MemberService memberService;

	public AuthService(
		MemberRepository memberRepository,
		PasswordEncoder passwordEncoder,
		JwtProvider jwtProvider,
		RefreshTokenRepository refreshTokenRepository,
		RefreshTokenHasher refreshTokenHasher,
		MemberService memberService) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
		this.refreshTokenRepository = refreshTokenRepository;
		this.refreshTokenHasher = refreshTokenHasher;
		this.memberService = memberService;
	}

	@Transactional
	public SignUpResponse signup(String name, String email, String password, Role role) {
		if (memberRepository.existsByEmail(email)) {
			throw new EmailAlreadyExistsException();
		}
		Member member = new Member(name, email, passwordEncoder.encode(password), role);
		memberRepository.save(member);
		return new SignUpResponse(member.getId());
	}

	@Transactional
	public TokenResponse login(String email, String password) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(InvalidCredentialException::new);

		if (!passwordEncoder.matches(password, member.getPassword())) {
			throw new InvalidCredentialException();
		}
		return issueTokens(member);
	}

	@Transactional
	public TokenResponse reissue(String refreshToken) {
		Long memberId;
		try {
			if (!jwtProvider.isRefreshToken(refreshToken)) {
				throw new InvalidRefreshTokenException();
			}
			memberId = jwtProvider.getMemberId(refreshToken);
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidRefreshTokenException();
		}
		String tokenHash = refreshTokenHasher.hash(refreshToken);

		RefreshToken savedToken = refreshTokenRepository
			.findByMemberIdAndTokenHash(memberId, tokenHash)
			.orElseThrow(InvalidRefreshTokenException::new);
		Member member = savedToken.getMember();

		return issueTokens(member);
	}

	@Transactional
	public void logout(Long memberId) {
		if (!memberRepository.existsById(memberId)) {
			throw new InvalidCredentialException();
		}
		refreshTokenRepository.deleteByMemberId(memberId);
	}

	@Transactional
	public void withdraw(Long memberId) {
		if (!memberRepository.existsById(memberId)) {
			throw new InvalidCredentialException();
		}
		refreshTokenRepository.deleteByMemberId(memberId);
		memberService.deleteMember(memberId);
	}

	private TokenResponse issueTokens(Member member) {
		String sessionId = UUID.randomUUID().toString();
		String accessToken = jwtProvider.createAccessToken(member.getId(), member.getRole());
		String refreshToken = jwtProvider.createRefreshToken(member.getId(), member.getRole());

		String tokenHash = refreshTokenHasher.hash(refreshToken);

		RefreshToken token = refreshTokenRepository
			.findByMemberId(member.getId())
			.orElseGet(() -> new RefreshToken(member, tokenHash, sessionId));

		token.updateToken(tokenHash, sessionId);
		refreshTokenRepository.save(token);

		return TokenResponse.of(accessToken, refreshToken);
	}
}
