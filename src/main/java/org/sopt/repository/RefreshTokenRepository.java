package org.sopt.repository;

import java.util.Optional;

import org.sopt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	public Optional<RefreshToken> findByMemberId(Long memberId);

	public boolean existsByMemberId(Long memberId);

	public Optional<RefreshToken> findByMemberIdAndTokenHash(Long memberId, String tokenHash);
	boolean existsByMemberIdAndSessionId(Long memberId, String sessionId);

	void deleteByMemberId(Long memberId);
}
