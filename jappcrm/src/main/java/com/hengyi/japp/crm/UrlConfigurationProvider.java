package com.hengyi.japp.crm;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.ConfigurationBuilder;

import com.hengyi.japp.common.CommonUrlConfigurationProvider;
import com.hengyi.japp.crm.Constant.Url;

public class UrlConfigurationProvider extends CommonUrlConfigurationProvider {
	@Override
	protected ConfigurationBuilder getConfigurationBuilder(
			ServletContext context) {
		ConfigurationBuilder builder = super.getConfigurationBuilder(context);
		urlService(builder, Url.corporationType, Url.certificate);
		return builder;
	}

	@Override
	public int priority() {
		return 10;
	}
}
