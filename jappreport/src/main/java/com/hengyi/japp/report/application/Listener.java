package com.hengyi.japp.report.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.common.eventbus.EventBus;
import com.hengyi.japp.common.event.EventPublisher;
import com.hengyi.japp.report.event.init.AppInitEvent;
import com.hengyi.japp.report.event.operator.LogoutEvent;
import com.hengyi.japp.report.service.CacheService;

public class Listener implements ServletContextListener, HttpSessionListener {
	private final Logger log = LoggerFactory.getLogger(getClass());

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(servletContextEvent
						.getServletContext());
		EventPublisher publisher = context.getBean(EventPublisher.class);
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
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(event.getSession()
						.getServletContext());
		CacheService cacheService = context.getBean(CacheService.class);
		EventBus eventBus = context.getBean(EventBus.class);
		try {
			eventBus.post(new LogoutEvent(cacheService.getCurrentOperator()));
		} catch (Exception e) {
			log.error("sessionDestroyed error:", e);
		}
	}
}