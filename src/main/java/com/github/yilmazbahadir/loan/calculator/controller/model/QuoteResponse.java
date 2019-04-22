package com.github.yilmazbahadir.loan.calculator.controller.model;

public final class QuoteResponse<T> {

	private final Status status;
	private final String message;
	private final T body;

	private QuoteResponse(Status status, String message, T body) {
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public static <T> QuoteResponse<T> ok(T body) {
		return new QuoteResponse<T>(Status.SUCCESS, "success", body);
	}

	public static <T> QuoteResponse<T> error(String message) {
		return new QuoteResponse<T>(Status.ERROR, message, null);
	}

	public Status getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public T getBody() {
		return body;
	}

	public enum Status {
		SUCCESS(200), ERROR(500);

		private int value;

		Status(int value) {
			this.value = value;
		}

		int getValue() {
			return this.value;
		}

	}
}
