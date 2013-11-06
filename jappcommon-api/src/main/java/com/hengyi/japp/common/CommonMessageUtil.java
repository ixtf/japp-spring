package com.hengyi.japp.common;

import com.hengyi.japp.common.CommonUtil;

public class CommonMessageUtil extends CommonUtil {
	public static final String info() {
		return messageResourceBundle().getString("info");
	}

	public static final String operationSuccess() {
		return messageResourceBundle().getString("operation.success");
	}

	public static final String operationFailure() {
		return messageResourceBundle().getString("operation.failure");
	}
}
