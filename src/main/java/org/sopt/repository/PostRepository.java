package org.sopt.repository;

import org.sopt.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
	Page<Post> findAll(Pageable pageable);

	void deleteByMemberId(long memberId);

	@Modifying
	@Query("update Post p set p.viewCount = p.viewCount + 1 where p.id = :postId")
	int incrementViewCount(@Param("postId") Long postId);
}
