package com.hengyi.japp.report;

import com.hengyi.japp.common.CommonUtil;

public class ErrorUtil extends CommonUtil {
	public static final String noChiefCommunicatee() {
		return errorResourceBundle().getString("noChiefCommunicatee");
	}

	public static String parentMenuIsSelf() {
		return errorResourceBundle().getString("parentMenuIsSelf");
	}
}
