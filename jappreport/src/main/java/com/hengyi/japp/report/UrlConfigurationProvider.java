package com.hengyi.japp.report;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;

import com.hengyi.japp.common.CommonUrlConfigurationProvider;

@RewriteConfiguration
public class UrlConfigurationProvider extends CommonUrlConfigurationProvider {

	@Override
	public int priority() {
		return 10;
	}
}
