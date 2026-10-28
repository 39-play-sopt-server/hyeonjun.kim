package org.sopt.repository;

import org.sopt.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
	Page<Post> findBySearchOption(Pageable pageable, Long memberId, String category, String title);
}
