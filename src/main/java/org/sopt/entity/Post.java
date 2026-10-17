package org.sopt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	private Category category;
	private Long viewCount = 0L;

	protected Post() {}

	public Post(String title, String content, Category category) {
		this.title = title;
		this.content = content;
		this.category = category;
	}

	public Post(Long id, String title, String content, Category category, Long viewCount) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.category = category;
		this.viewCount = viewCount;
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
