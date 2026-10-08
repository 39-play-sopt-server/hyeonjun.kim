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

	public static <T> ApiResponse<T> success(SuccessCode successCode, T data) {
		return new ApiResponse<>(successCode.getStatus(), successCode.toString(), successCode.getMessage(), data);
	}

	public static <T> ApiResponse<T> success(SuccessCode successCode) {
		return new ApiResponse<>(successCode.getStatus(), successCode.toString(), successCode.getMessage(), null);
	}

	public static <T> ApiResponse<T> failure(ErrorCode errorCode) {
		return new ApiResponse<>(errorCode.getStatus(), errorCode.toString(), errorCode.getMessage(), null);
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
