package com.hengyi.japp.report.event.report;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.context.ApplicationListener;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;
import com.hengyi.japp.report.domain.ReportAccess;

@Named
@Singleton
@Transactional
public class ReportAccessEventListener implements
		ApplicationListener<ReportAccessEvent> {
	@Resource
	private Neo4jOperations template;
	@Resource
	private EventBus eventBus;

	@Override
	public void onApplicationEvent(ReportAccessEvent event) {
		synchronized (ReportAccessEventListener.class) {
			ReportAccess reportAccess = template.createRelationshipBetween(
					event.getOperator(), event.getReport(), ReportAccess.class,
					ReportAccess.RELATIONSHIP, false);
			reportAccess.addAccessCount();
			template.save(reportAccess);
		}
	}
}
