package org.sopt.entity;

import org.sopt.exception.InvalidCategoryException;

public enum Category {
	NOTICE,
	FREE,
	QUESTION;

	public static Category fromNumber(int number) {
		return switch (number) {
			case 1 -> Category.NOTICE;
			case 2 -> Category.FREE;
			case 3 -> Category.QUESTION;
			default -> throw new InvalidCategoryException();
		};
	}
}
