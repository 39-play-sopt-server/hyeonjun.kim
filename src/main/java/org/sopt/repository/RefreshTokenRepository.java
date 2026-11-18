package org.sopt.repository;

import java.util.Optional;

import org.sopt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByMemberId(Long memberId);

	boolean existsByMemberId(Long memberId);

	Optional<RefreshToken> findByMemberIdAndTokenHash(Long memberId, String tokenHash);


	void deleteByMemberId(Long memberId);
}
