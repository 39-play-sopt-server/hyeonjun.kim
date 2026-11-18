package org.sopt.config;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.sopt.entity.Role;
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
	private final long refreshTokenExpiration;

	public JwtProvider(
		@Value("${spring.jwt.secret}") String secret,
		@Value("${spring.jwt.access-token-expiration}") long accessTokenExpiration,
		@Value("${spring.jwt.refresh-token-expiration}") long refreshTokenExpiration
	) {
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
		this.accessTokenExpiration = accessTokenExpiration;
		this.refreshTokenExpiration = refreshTokenExpiration;
	}

	public String createToken(Long memberId, String type, long expiration, Role role) {
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + expiration);

		return Jwts.builder()
			.subject(String.valueOf(memberId))
			.id(UUID.randomUUID().toString())
			.claim("type", type)
			.claim("role", role.name())
			.issuedAt(now)
			.expiration(expirationDate)
			.signWith(secretKey)
			.compact();
	}

	public String createAccessToken(Long memberId, Role role) {
		return createToken(memberId, "ACCESS",  accessTokenExpiration, role);
	}

	public String createRefreshToken(Long memberId, Role role) {
		return createToken(memberId, "REFRESH",  refreshTokenExpiration, role);
	}

	public Claims parseClaims(String token) {
		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)  // 서명, 만료 검증 → 실패 시 JwtException
			.getPayload();
	}

	public Long getMemberId(String token) {
		return Long.valueOf(parseClaims(token).getSubject());
	}

	public String getTokenType(String token) {
		return parseClaims(token).get("type", String.class);
	}

	public Role getRole(String token) {
		return Role.valueOf(parseClaims(token).get("role", String.class));
	}

	public boolean validateToken(String token) {
		try {
			parseClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isAccessToken(String token) {
		return "ACCESS".equals(getTokenType(token));
	}

	public boolean isRefreshToken(String token) {
		return "REFRESH".equals(getTokenType(token));
	}
}
