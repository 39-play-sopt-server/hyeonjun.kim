package org.sopt.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false, unique = true)
	private Member member;

	private String tokenHash;
	private String sessionId;

	protected RefreshToken() {}

	public RefreshToken(Member member, String tokenHash, String sessionId) {
		this.member = member;
		this.tokenHash = tokenHash;
		this.sessionId = sessionId;
	}

	public Long getId() {
		return id;
	}

	public Member getMember() {
		return member;
	}

	public String getTokenHash() {
		return tokenHash;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void updateToken(String tokenHash, String sessionId) {
		this.tokenHash = tokenHash;
		this.sessionId = sessionId;
	}
}
