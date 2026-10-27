package org.sopt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Post extends BaseEntity{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String content;

	@Enumerated(EnumType.STRING)
	private Category category;

	private Long viewCount = 0L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	protected Post() {}

	public Post(Member member, String title, String content, Category category) {
		this.member = member;
		this.title = title;
		this.content = content;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Category getCategory() {
		return category;
	}
	public Long getViewCount() {
		return viewCount;
	}
	public Member getMember() {
		return member;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "Post{" +
			"id=" + id +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			", category=" + category +
			", viewCount=" + viewCount +
			'}';
	}
}
