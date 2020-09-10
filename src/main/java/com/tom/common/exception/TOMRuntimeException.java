package com.tom.common.exception;

/**
 * @author Chandani Joshi
 *
 */
@SuppressWarnings("serial")
public class TOMRuntimeException extends RuntimeException {
	private final Exception e;

	public TOMRuntimeException(String message, Exception e) {
		super(message);
		this.e = e;
	}

	public Exception getException() {
		return this.e;
	}
}
