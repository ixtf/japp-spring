package com.hengyi.japp.crm;

import com.hengyi.japp.common.CommonUtil;

public class MessageUtil extends CommonUtil {
	public static String info() {
		return messageResourceBundle().getString("info");
	}

	public static final String operationSuccess() {
		return messageResourceBundle().getString("operation.success");
	}

	public static final String operationFailure() {
		return messageResourceBundle().getString("operation.failure");
	}
}
