package com.hengyi.japp.trans;

import com.hengyi.japp.common.CommonUrlConfigurationProvider;

public class UrlConfigurationProvider extends CommonUrlConfigurationProvider {

	@Override
	public int priority() {
		return 10;
	}
}
