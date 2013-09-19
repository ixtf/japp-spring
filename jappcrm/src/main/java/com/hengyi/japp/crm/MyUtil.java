package com.hengyi.japp.crm;

import org.apache.commons.lang3.StringUtils;

import com.hengyi.japp.common.CommonUtil;
import com.hengyi.japp.crm.exception.SearchException;

public class MyUtil extends CommonUtil {
	public static void checkSearch(String s) throws Exception {
		if (StringUtils.isBlank(s))
			throw new SearchException(s);
		String _s = StringUtils.replace(s, "*", "");
		if (StringUtils.isBlank(_s))
			throw new SearchException(_s);
	}
}
