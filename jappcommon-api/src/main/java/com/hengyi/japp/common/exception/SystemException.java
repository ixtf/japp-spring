package com.hengyi.japp.common.exception;

@SuppressWarnings("serial")
public class SystemException extends RuntimeException {

	public SystemException() {
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}
}
