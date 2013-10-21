package com.hengyi.japp.report.web;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.event.EventPublisher;
import com.hengyi.japp.report.Constant;
import com.hengyi.japp.report.MessageUtil;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.finereport.OpType;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.service.CacheService;
import com.hengyi.japp.report.service.MenuService;
import com.hengyi.japp.report.service.OperatorService;
import com.hengyi.japp.report.service.ReportFactory;
import com.hengyi.japp.report.service.RoleService;

public abstract class AbstractController {
	protected final Logger log = LoggerFactory.getLogger(getClass());
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

	public List<PrincipalType> getAllPrincipalTypes() {
		return Lists.newArrayList(PrincipalType.values());
	}

	public int getPageSize() {
		return Constant.PAGE_SIZE;
	}

	public Operator getCurrentOperator() throws Exception {
		return cacheService.getCurrentOperator();
	}

	protected void redirect(String url) {
		StringBuilder sb;
		try {
			if (url.indexOf("http://") == 0)
				sb = new StringBuilder(url);
			else {
				sb = new StringBuilder("/report");
				if (url.substring(0, 1).equals("/"))
					sb.append(url);
				else
					sb.append("/").append(url);
			}
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(sb.toString());
		} catch (Exception e) {
			log.error("", e);
			errorMessage(e);
		}
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

	protected void operationSuccessMessage() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, MessageUtil
						.operationSuccess(), null));
	}

	protected void infoMessage(String s) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						MessageUtil.info(), s));
	}

	protected void errorMessage(Exception e) {
		log.error("", e);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtil
						.operationFailure(), e.getLocalizedMessage()));
	}

	protected HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	protected String getParameter(String p) {
		return getHttpServletRequest().getParameter(p);
	}
}
