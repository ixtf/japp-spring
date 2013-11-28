package com.hengyi.japp.report.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hengyi.japp.common.event.EventPublisher;
import com.hengyi.japp.report.Constant;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.event.init.AppInitEvent;
import com.hengyi.japp.report.event.operator.LogoutEvent;

public class Listener implements ServletContextListener, HttpSessionListener {
	private final Logger log = LoggerFactory.getLogger(getClass());
	EventPublisher publisher;

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(servletContextEvent
						.getServletContext());
		publisher = context.getBean(EventPublisher.class);
		publisher.publish(new AppInitEvent(context.getServletContext()));
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		log.info("*******contextDestroyed*********");
	}

	public void sessionCreated(HttpSessionEvent event) {
		// try {
		// cacheService.setSessionData();
		// } catch (AppException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		if (session == null)
			return;
		Object operator = session.getAttribute(Constant.SESSION_OPERATOR);
		if (operator == null)
			return;
		publisher.publish(new LogoutEvent((Operator) operator));
	}
}