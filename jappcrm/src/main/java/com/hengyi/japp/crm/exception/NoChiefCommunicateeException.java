package com.hengyi.japp.crm.exception;

import com.hengyi.japp.crm.ErrorUtil;


public class NoChiefCommunicateeException extends Exception {
	private static final long serialVersionUID = 5494945099785702307L;

	@Override
	public String getMessage() {
		return ErrorUtil.noChiefCommunicatee();
	}
}
