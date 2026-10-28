package org.sopt.repository;

import java.util.Optional;

import org.sopt.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
	Optional<PostLike> findByMemberIdAndPostId(Long memberId, Long postId);

	int countByPostId(Long postId);

	boolean existsByMemberIdAndPostId(Long memberId, Long postId);

	void deleteByMemberId(Long memberId);

	void deleteByPostMemberId(Long memberId);

	void deleteByPostId(Long postId);
}
