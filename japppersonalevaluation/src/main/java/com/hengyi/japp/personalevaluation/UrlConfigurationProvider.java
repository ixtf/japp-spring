package com.hengyi.japp.personalevaluation;

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

import com.hengyi.japp.personalevaluation.Constant.JSF;
import com.hengyi.japp.personalevaluation.Constant.URL;

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
				.perform(Redirect.temporary(context.getContextPath() + "/"))
				.addRule(Join.path(URL.LOGIN).to(JSF.LOGIN))
				// .addRule()
				// .when(Direction.isInbound().and(Path.matches(URL.LOGOUT)))
				// .perform(
				// Invoke.binding(
				// El.retrievalMethod("#{session.invalidate}"))
				// .and(Redirect
				// .temporary("http://cas.hengyi.com:8080/logout?service=http://192.168.17.117:8080/personalevaluation")))
				.addRule()
				.when(Direction.isInbound().and(Path.matches("/")).and(admin))
				.perform(
						Redirect.temporary(context.getContextPath() + "/admin"))
				.addRule(Join.path(URL.HOME).to(JSF.HOME))
				.addRule(Join.path(URL.TASKS).to(JSF.TASKS))
				.addRule(Join.path(URL.TASK_NEW).to(JSF.TASK_UPDATE))
				.addRule(Join.path("/admin").to("/faces/admin/index.jsf"))
				.when(admin)
				.addRule(Join.path(URL.TASK_CHANGE).to(JSF.TASK_CHANGE))
				.when(loggedIn).addRule(Join.path(URL.THEME).to(JSF.THEME))
				.when(loggedIn);
	}

	@Override
	public int priority() {
		return 10;
	}
}
