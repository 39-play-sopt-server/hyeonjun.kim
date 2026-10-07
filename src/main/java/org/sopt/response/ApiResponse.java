package org.sopt.response;

public class ApiResponse<T> {
	private final int status;
	private final String code;
	private final String message;
	private final T data;

	private ApiResponse(int status, String code, String message, T data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> success(int status, String code, String message, T data) {
		return new ApiResponse<>(status, code, message, data);
	}

	public static <T> ApiResponse<T> success(int status, String code, String message) {
		return new ApiResponse<>(status, code, message, null);
	}

	public static <T> ApiResponse<T> failure(int status, String code, String message) {
		return new ApiResponse<>(status, code, message, null);
	}

	public int getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}
}
