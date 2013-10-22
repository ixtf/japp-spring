package com.hengyi.japp.common;

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

import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.common.service.CommonCrudService;

public class CommonUrlConfigurationProvider extends HttpConfigurationProvider {
	@Override
	public Configuration getConfiguration(ServletContext context) {
		return getConfigurationBuilder(context);
	}

	protected ConfigurationBuilder getConfigurationBuilder(
			ServletContext context) {
		WebApplicationContext springContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
		CommonCacheService cacheService = springContext
				.getBean(CommonCacheService.class);
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
		ConfigurationBuilder builder = ConfigurationBuilder
				.begin()
				.addRule()
				.when(Direction.isInbound()
						.and(Path.matches(cacheService.getLoginPath()))
						.and(loggedIn))
				.perform(
						Redirect.temporary(context.getContextPath()
								+ cacheService.getHomePath()))
				.addRule(
						Join.path(cacheService.getLoginPath()).to(
								cacheService.getLoginView()))
				.addRule()
				.when(Direction.isInbound()
						.and(Path.matches(cacheService.getHomePath()))
						.and(admin))
				.perform(
						Redirect.temporary(context.getContextPath()
								+ cacheService.getAdminHomePath()))
				.addRule(
						Join.path(cacheService.getAdminHomePath()).to(
								cacheService.getAdminHomeView()))
				.addRule(
						Join.path(cacheService.getThemePath()).to(
								cacheService.getThemeView()))
				.when(loggedIn)
				.addRule(
						Join.path(cacheService.getHomePath()).to(
								cacheService.getHomeView()));

		for (CommonCrudService<?, ?> urlService : springContext.getBeansOfType(
				CommonCrudService.class).values())
			urlService(builder, urlService);

		return builder;
	}

	private void urlService(ConfigurationBuilder builder,
			CommonCrudService<?, ?> urlService) {
		builder.addRule(
				Join.path(urlService.getNewPath()).to(urlService.getNewView()))
				.addRule(
						Join.path(urlService.getUpdatePath()).to(
								urlService.getUpdateView()))
				.addRule(
						Join.path(urlService.getManagePath()).to(
								urlService.getManageView()));
	}

	@Override
	public int priority() {
		return 10;
	}
}
