package com.hengyi.japp.crm;

import java.text.MessageFormat;

import com.hengyi.japp.common.CommonUtil;
import com.hengyi.japp.crm.domain.Indicator;

public class ErrorUtil extends CommonUtil {
	public static final String noChiefCommunicatee() {
		return CommonUtil.errorResourceBundle()
				.getString("noChiefCommunicatee");
	}

	public static final String noIndicatorValue(Iterable<Indicator> indicators) {
		String result = errorResourceBundle().getString("noIndicatorValue");
		return MessageFormat.format(result, indicators);
	}
}
