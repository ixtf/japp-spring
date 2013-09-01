package com.hengyi.japp.crm;

import static com.hengyi.japp.common.Constant.ADMIN_PRINCIPAL;

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

import com.hengyi.japp.crm.Constant.JSF;
import com.hengyi.japp.crm.Constant.URL;

public class UrlConfigurationProvider extends HttpConfigurationProvider {

	@Override
	public Configuration getConfiguration(ServletContext context) {
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
				.when(Direction.isInbound().and(Path.matches(URL.LOGIN))
						.and(loggedIn))
				.perform(
						Redirect.temporary(context.getContextPath() + URL.HOME))
				.addRule(Join.path(URL.LOGIN).to(JSF.LOGIN))
				.addRule()
				.when(Direction.isInbound().and(Path.matches(URL.HOME))
						.and(admin))
				.perform(
						Redirect.temporary(context.getContextPath() + URL.ADMIN))
				.addRule(Join.path(URL.ADMIN).to(JSF.ADMIN))
				.addRule(Join.path(URL.THEME).to(JSF.THEME))
				.when(loggedIn)
				.addRule(Join.path(URL.HOME).to(JSF.HOME))
				.addRule(
						Join.path(URL.COMMUNICATEE_NEW)
								.to(JSF.COMMUNICATEE_NEW))
				.addRule(Join.path(URL.COMMUNICATEES).to(JSF.COMMUNICATEES))
				.addRule(Join.path(URL.CRMTYPE_NEW).to(JSF.CRMTYPE_NEW))
				.addRule(Join.path(URL.CRMTYPES).to(JSF.CRMTYPES))
				.addRule(
						Join.path(URL.INDICATORVALUE_NEW).to(
								JSF.INDICATORVALUE_NEW))
				.addRule(Join.path(URL.INDICATORVALUES).to(JSF.INDICATORVALUES))
				.addRule(Join.path(URL.CUSTOMER_NEW).to(JSF.CUSTOMER_NEW))
				.addRule(Join.path(URL.CUSTOMERS).to(JSF.CUSTOMERS))
				.addRule(Join.path(URL.STORAGE_NEW).to(JSF.STORAGE_NEW))
				.addRule(Join.path(URL.STORAGES).to(JSF.STORAGES))
				// .addRule(Join.path(URL.INDICATORS).to(JSF.INDICATORS))
				.addRule(
						Join.path(URL.CUSTOMER_INDICATOR_NEW).to(
								JSF.CUSTOMER_INDICATOR_NEW))
				.addRule(
						Join.path(URL.CUSTOMER_INDICATORS).to(
								JSF.CUSTOMER_INDICATORS))
				.addRule(
						Join.path(URL.CUSTOMER_INDICATOR_NEW).to(
								JSF.CUSTOMER_INDICATOR_NEW))
				.addRule(
						Join.path(URL.CUSTOMER_INDICATORS).to(
								JSF.CUSTOMER_INDICATORS))
				.addRule(
						Join.path(URL.STORAGE_INDICATOR_NEW).to(
								JSF.STORAGE_INDICATOR_NEW))
				.addRule(
						Join.path(URL.STORAGE_INDICATORS).to(
								JSF.STORAGE_INDICATORS))
				.addRule(
						Join.path(URL.STORAGE_INDICATOR_NEW).to(
								JSF.STORAGE_INDICATOR_NEW))
				.addRule(
						Join.path(URL.STORAGE_INDICATORS).to(
								JSF.STORAGE_INDICATORS));
	}

	@Override
	public int priority() {
		return 10;
	}
}
