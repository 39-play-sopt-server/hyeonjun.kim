package org.sopt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
	name = "post_like",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = {"member_id", "post_id"})
	}
)
public class PostLike extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;

	protected PostLike() {}

	public PostLike(Member member, Post post) {
		this.member = member;
		this.post = post;
	}

	public Member getMember() {
		return member;
	}

	public Post getPost() {
		return post;
	}
}
