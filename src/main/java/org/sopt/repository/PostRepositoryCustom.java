package org.sopt.repository;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
	Page<Post> findBySearchOption(Pageable pageable, Long memberId, Category category, String title);
}
