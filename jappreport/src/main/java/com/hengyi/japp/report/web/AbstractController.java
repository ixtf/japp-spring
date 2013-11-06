package com.hengyi.japp.report.web;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.hengyi.japp.common.event.EventPublisher;
import com.hengyi.japp.common.web.CommonAbstractController;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.finereport.OpType;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.service.CacheService;
import com.hengyi.japp.report.service.MenuService;
import com.hengyi.japp.report.service.OperatorService;
import com.hengyi.japp.report.service.ReportFactory;
import com.hengyi.japp.report.service.RoleService;

public abstract class AbstractController extends CommonAbstractController {
	@Resource
	protected Neo4jOperations template;
	@Resource
	protected EventBus eventBus;
	@Inject
	protected EventPublisher eventPublisher;
	@Inject
	protected CacheService cacheService;
	@Inject
	protected OperatorService operatorService;
	@Inject
	protected RoleService roleService;
	@Inject
	protected MenuService menuService;
	@Inject
	protected ReportFactory reportFactory;
	@Resource
	protected ReportRepository reportRepository;

	public List<OpType> getAllOpTypes() {
		return Lists.newArrayList(OpType.values());
	}

	public Operator getCurrentOperator() throws Exception {
		return cacheService.getCurrentOperator();
	}

	protected void push(String s) {
		PushContext pushContext = PushContextFactory.getDefault()
				.getPushContext();
		try {
			pushContext
					.push("/" + getCurrentOperator().getUuid(),
							new FacesMessage(FacesMessage.SEVERITY_INFO, s,
									"Success!"));
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	protected void push(FacesMessage facesMessage) {
		PushContext pushContext = PushContextFactory.getDefault()
				.getPushContext();
		try {
			pushContext
					.push("/" + getCurrentOperator().getUuid(), facesMessage);
		} catch (Exception e) {
			errorMessage(e);
		}
	}
}
