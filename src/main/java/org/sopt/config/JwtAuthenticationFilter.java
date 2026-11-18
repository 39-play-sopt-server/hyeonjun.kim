package org.sopt.config;

import java.io.IOException;
import java.util.List;

import org.jspecify.annotations.NonNull;
import org.sopt.entity.Role;
import org.sopt.repository.RefreshTokenRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String BEARER_PREFIX = "Bearer ";

	private final JwtProvider jwtProvider;
	private final RefreshTokenRepository refreshTokenRepository;

	public JwtAuthenticationFilter(JwtProvider jwtProvider, RefreshTokenRepository refreshTokenRepository) {
		this.jwtProvider = jwtProvider;
		this.refreshTokenRepository = refreshTokenRepository;
	}

	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain) throws ServletException, IOException {
		String token = resolveToken(request);

		if (token != null) {
			try {
				if (!jwtProvider.isAccessToken(token)) {
					throw new JwtException("Access token이 아닙니다.");
				}
				Long memberId = jwtProvider.getMemberId(token);
				if (!refreshTokenRepository.existsByMemberId(memberId)) {
					throw new JwtException("로그아웃되었거나 만료된 세션입니다.");
				}
				Role role = jwtProvider.getRole(token);

				// 인증 완료된 Authentication 생성 후 SecurityContext에 저장
				UsernamePasswordAuthenticationToken authentication =
					new UsernamePasswordAuthenticationToken(memberId, null, List.of(new SimpleGrantedAuthority(role.name())));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (JwtException | IllegalArgumentException e) {
				// 유효하지 않은 토큰이면 인증 정보를 저장하지 않음
			}
		}

		filterChain.doFilter(request, response);
	}

	private String resolveToken(HttpServletRequest request) {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header != null && header.startsWith(BEARER_PREFIX)) {
			return header.substring(BEARER_PREFIX.length());
		}
		return null;
	}
}
