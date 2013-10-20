package com.hengyi.japp.report.exception;

import com.hengyi.japp.report.ErrorUtil;

public class ParentMenuIsSelfException extends Exception {
	private static final long serialVersionUID = 5494945099785702307L;

	public ParentMenuIsSelfException() {
		super();
	}

	@Override
	public String getMessage() {
		return ErrorUtil.parentMenuIsSelf();
	}
}
