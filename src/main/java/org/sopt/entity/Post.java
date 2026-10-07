package org.sopt.entity;

public class Post {
	private final Long id;
	private String title;
	private String content;
	private final Category category;

	public Post(Long id, String title, String content, Category category) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Post{" +
			"id=" + id +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			", category=" + category +
			'}';
	}
}
