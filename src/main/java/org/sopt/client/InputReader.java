package org.sopt.client;

import java.util.Scanner;

public class InputReader {
	private final Scanner scanner = new Scanner(System.in);

	public String readString() {
		return scanner.nextLine();
	}

	public int readInt() {
		return Integer.parseInt(scanner.nextLine());
	}

	public long readLong() {
		return Long.parseLong(scanner.nextLine());
	}
}
