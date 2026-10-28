package org.sopt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Member extends BaseEntity{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;

	protected Member() {}

	public Member(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
