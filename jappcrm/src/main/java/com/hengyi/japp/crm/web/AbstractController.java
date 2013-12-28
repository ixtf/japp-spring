package com.hengyi.japp.crm.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.hengyi.japp.common.event.EventPublisher;
import com.hengyi.japp.common.event.SyncEventPublisher;
import com.hengyi.japp.crm.Constant;
import com.hengyi.japp.crm.MessageUtil;
import com.hengyi.japp.crm.data.CrmFieldType;
import com.hengyi.japp.crm.domain.Operator;
import com.hengyi.japp.crm.service.CacheService;
import com.hengyi.japp.crm.service.CommunicateeService;
import com.hengyi.japp.crm.service.CrmFieldService;
import com.hengyi.japp.crm.service.IndicatorValueService;
import com.hengyi.japp.crm.service.QueryService;

public abstract class AbstractController {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	protected EventBus eventBus;
	@Inject
	protected Neo4jTemplate template;
	@Inject
	protected QueryService queryService;
	@Inject
	protected CacheService cacheService;
	@Inject
	protected EventPublisher eventPublisher;
	@Inject
	protected SyncEventPublisher syncEventPublisher;
	@Inject
	protected CommunicateeService communicateeService;
	@Inject
	protected IndicatorValueService indicatorValueService;
	@Inject
	protected CrmFieldService crmFieldService;

	public List<CrmFieldType> getAllCrmFieldTypes() {
		return Lists.newArrayList(CrmFieldType.values());
	}

	public int getPageSize() {
		return Constant.PAGE_SIZE;
	}

	protected void redirect(String url) {
		String prefix = "/crm";
		try {
			if (url.indexOf("http") >= 0)
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(url);

			if (!url.substring(0, 1).equals("/"))
				prefix = prefix + "/";
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(prefix + url);
		} catch (Exception e) {
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

	protected void push(FacesMessage facesMessage) throws Exception {
		PushContext pushContext = PushContextFactory.getDefault()
				.getPushContext();
		pushContext.push("/" + getCurrentOperator().getUuid(), facesMessage);
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

	public Operator getCurrentOperator() throws Exception {
		return cacheService.getCurrentOperator();
	}
}
