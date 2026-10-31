package org.sopt.repository;

import static org.sopt.entity.QPost.*;

import java.util.List;

import org.sopt.entity.Category;
import org.sopt.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {
	@Autowired
	private JPAQueryFactory queryFactory;

	public PostRepositoryImpl() {
		super(Post.class);
	}

	@Override
	public Page<Post> findBySearchOption(Pageable pageable, Long memberId, Category category, String title) {
		JPQLQuery<Post> query = queryFactory.selectFrom(post).where(eqMemberId(memberId), eqCategory(category), titleContains(title));

		List<Post> posts = this.getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<Post>(posts, pageable, query.fetchCount());
	}

	private BooleanExpression eqMemberId(Long memberId) {
		if (memberId == null) {
			return null;
		}
		return post.member.id.eq(memberId);
	}

	private BooleanExpression eqCategory(Category category) {
		if (category == null) {
			return null;
		}
		return post.category.eq(category);
	}

	private BooleanExpression titleContains(String title) {
		if (title == null || title.isBlank()) {
			return null;
		}
		return post.title.containsIgnoreCase(title);
	}
}
