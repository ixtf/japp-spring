package com.hengyi.japp.crm.exception;

public class SearchException extends Exception {
	private static final long serialVersionUID = 5494945099785702307L;
	private final String s;

	public SearchException(String s) {
		super();
		this.s = s;
	}

	@Override
	public String getMessage() {
		return s;
	}
}
