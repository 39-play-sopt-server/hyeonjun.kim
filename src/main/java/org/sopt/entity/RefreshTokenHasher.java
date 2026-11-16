package org.sopt.entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import org.springframework.stereotype.Component;

@Component
public class RefreshTokenHasher {

	public String hash(String token) {
		try {
			MessageDigest digest =
				MessageDigest.getInstance("SHA-256");

			byte[] hashed = digest.digest(
				token.getBytes(StandardCharsets.UTF_8)
			);

			return HexFormat.of().formatHex(hashed);

		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}
}
