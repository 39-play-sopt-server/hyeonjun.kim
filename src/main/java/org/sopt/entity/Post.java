package org.sopt.entity;

public class Post {
	private final Long id;
	private String title;
	private String content;
	private final Category category;
	private Long viewCount;

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
