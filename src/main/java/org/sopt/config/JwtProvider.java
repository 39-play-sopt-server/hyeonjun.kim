package org.sopt.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
	private final SecretKey secretKey;
	private final long accessTokenExpiration;

	public JwtProvider(
		@Value("${spring.jwt.secret}") String secret,
		@Value("${spring.jwt.access-token-expiration}") long accessTokenExpiration
	) {
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
		this.accessTokenExpiration = accessTokenExpiration;
	}

	public String createAccessToken(Long memberId) {
		Date now = new Date();

		return Jwts.builder()
			.subject(String.valueOf(memberId))   // 누구의 토큰인지
			.issuedAt(now)                       // 발급 시각
			.expiration(new Date(now.getTime() + accessTokenExpiration)) // 만료 시각
			.signWith(secretKey)                 // 서명
			.compact();
	}

	public Long getMemberId(String token) {
		Claims claims = Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)  // 서명, 만료 검증 → 실패 시 JwtException
			.getPayload();

		return Long.valueOf(claims.getSubject());
	}
}
