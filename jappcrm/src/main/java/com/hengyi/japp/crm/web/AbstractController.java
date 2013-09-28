package com.hengyi.japp.crm.web;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.dozer.Mapper;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.Constant;
import com.hengyi.japp.crm.MessageUtil;
import com.hengyi.japp.crm.data.CrmField;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Operator;
import com.hengyi.japp.crm.service.CacheService;
import com.hengyi.japp.crm.service.CertificateService;
import com.hengyi.japp.crm.service.CommunicateeService;
import com.hengyi.japp.crm.service.CrmTypeService;
import com.hengyi.japp.crm.service.IndicatorValueService;
import com.hengyi.japp.crm.service.OperatorService;

public abstract class AbstractController {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	protected Neo4jOperations template;
	@Resource
	protected Mapper dozer;
	@Inject
	protected CacheService cacheService;
	@Inject
	protected OperatorService operatorService;
	@Inject
	protected CrmTypeService crmTypeService;
	@Inject
	protected CertificateService certificateService;
	@Inject
	protected CommunicateeService communicateeService;
	@Inject
	protected IndicatorValueService indicatorValueService;

	public List<CrmType> getAllCrmTypes() {
		return Lists.newArrayList(crmTypeService.findAll());
	}

	public List<Certificate> getAllCertificates() {
		return Lists.newArrayList(certificateService.findAll());
	}

	public List<CrmField> getAllCrmFields() {
		return Lists.newArrayList(CrmField.values());
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
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageUtil
						.operationFailure(), e.getLocalizedMessage()));
	}

	public Operator getCurrentOperator() throws Exception {
		return cacheService.getCurrentOperator();
	}
}
