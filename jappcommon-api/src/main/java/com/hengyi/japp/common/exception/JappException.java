package com.hengyi.japp.common.exception;

import java.util.Arrays;
import java.util.List;

public class JappException extends Exception {
	private static final long serialVersionUID = 8321444123642660056L;

	private String errorCode;
	private List<Object> params;

	public JappException(String errorCode) {
		super(errorCode);
		params = null;
		this.errorCode = errorCode;
	}

	public JappException(String errorCode, Object... params) {
		super(errorCode);
		initParam(errorCode, params);
	}

	public JappException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public JappException(String errorCode, Throwable cause, Object... params) {
		super(cause);
		initParam(errorCode, params);
	}

	private void initParam(String errorCode, Object... params) {
		this.errorCode = errorCode;
		this.params = Arrays.asList(params);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}
}
