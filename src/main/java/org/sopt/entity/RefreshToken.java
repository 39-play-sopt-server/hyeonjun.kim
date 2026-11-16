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
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	private String tokenHash;

	protected RefreshToken() {}

	public RefreshToken(Member member, String tokenHash) {
		this.member = member;
		this.tokenHash = tokenHash;
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

	public void updateTokenHash(String tokenHash) {
		this.tokenHash = tokenHash;
	}
}
