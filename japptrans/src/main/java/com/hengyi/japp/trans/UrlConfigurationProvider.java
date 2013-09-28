package com.hengyi.japp.trans;

import static com.hengyi.japp.common.CommonConstant.ADMIN_PRINCIPAL;

import javax.servlet.ServletContext;

import org.apache.shiro.SecurityUtils;
import org.ocpsoft.rewrite.config.Condition;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.event.Rewrite;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class UrlConfigurationProvider extends HttpConfigurationProvider {

	@Override
	public Configuration getConfiguration(ServletContext context) {
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(context);
		UrlUtil urlUtil = springContext.getBean(UrlUtil.class);

		Condition loggedIn = new Condition() {
			@Override
			public boolean evaluate(Rewrite event, EvaluationContext context) {
				return SecurityUtils.getSubject().isAuthenticated();
			}
		};
		Condition admin = new Condition() {
			@Override
			public boolean evaluate(Rewrite event, EvaluationContext context) {
				return SecurityUtils.getSubject().hasRole(ADMIN_PRINCIPAL);
			}
		};

		return ConfigurationBuilder
				.begin()
				.addRule()
				.when(Direction.isInbound()
						.and(Path.matches(urlUtil.getLoginPath()))
						.and(loggedIn))
				.perform(
						Redirect.temporary(context.getContextPath()
								+ urlUtil.getHomePath()))
				.addRule(
						Join.path(urlUtil.getLoginPath()).to(
								urlUtil.getLoginView()))
				.addRule()
				.when(Direction.isInbound()
						.and(Path.matches(urlUtil.getHomePath())).and(admin))
				.perform(
						Redirect.temporary(context.getContextPath()
								+ urlUtil.getAdminHomePath()))
				.addRule(
						Join.path(urlUtil.getAdminHomePath()).to(
								urlUtil.getAdminHomeView()))
				.addRule(
						Join.path(urlUtil.getThemePath()).to(
								urlUtil.getThemeView()))
				.when(loggedIn)
				.addRule(
						Join.path(urlUtil.getHomePath()).to(
								urlUtil.getHomeView()))

				.addRule(
						Join.path(urlUtil.getYsDeliveryNewPath()).to(
								urlUtil.getYsDeliveryUpdateView()))
				.addRule(
						Join.path(urlUtil.getYsDeliveriesPath()).to(
								urlUtil.getYsDeliverysView()))

				.addRule(
						Join.path(urlUtil.getYsPickNewPath()).to(
								urlUtil.getYsPickUpdateView()))
				.addRule(
						Join.path(urlUtil.getYsPicksPath()).to(
								urlUtil.getYsPicksView()))

				.addRule(
						Join.path(urlUtil.getCommunicateeNewPath()).to(
								urlUtil.getCommunicateeUpdateView()))
				.addRule(
						Join.path(urlUtil.getCommunicateesPath()).to(
								urlUtil.getCommunicateesView()))

				.addRule(
						Join.path(urlUtil.getIndicatorValueNewPath()).to(
								urlUtil.getIndicatorValueUpdateView()))
				.addRule(
						Join.path(urlUtil.getIndicatorValuesPath()).to(
								urlUtil.getIndicatorValuesView()))

				.addRule(
						Join.path(urlUtil.getCustomerIndicatorNewPath()).to(
								urlUtil.getCustomerIndicatorUpdateView()))
				.addRule(
						Join.path(urlUtil.getCustomerIndicatorsPath()).to(
								urlUtil.getCustomerIndicatorsView()))
				.addRule(
						Join.path(urlUtil.getCustomerNewPath()).to(
								urlUtil.getCustomerUpdateView()))
				.addRule(
						Join.path(urlUtil.getCustomersPath()).to(
								urlUtil.getCustomersView()))

				.addRule(
						Join.path(urlUtil.getStorageIndicatorNewPath()).to(
								urlUtil.getStorageIndicatorUpdateView()))
				.addRule(
						Join.path(urlUtil.getStorageIndicatorsPath()).to(
								urlUtil.getStorageIndicatorsView()))
				.addRule(
						Join.path(urlUtil.getStorageNewPath()).to(
								urlUtil.getStorageUpdateView()))
				.addRule(
						Join.path(urlUtil.getStoragesPath()).to(
								urlUtil.getStoragesView()))
				.addRule(
						Join.path(urlUtil.getStoragesPath()).to(
								urlUtil.getStoragesView()));
	}

	@Override
	public int priority() {
		return 10;
	}
}
