package com.hengyi.japp.crm.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hengyi.japp.crm.Constant;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Operator;
import com.hengyi.japp.crm.domain.repository.CrmTypeRepository;
import com.hengyi.japp.crm.service.CacheServiceFacade;
import com.hengyi.japp.crm.service.CommunicateeService;
import com.hengyi.japp.crm.service.CustomerService;
import com.hengyi.japp.crm.service.IndicatorService;
import com.hengyi.japp.crm.service.IndicatorValueService;
import com.hengyi.japp.crm.service.OperatorService;
import com.hengyi.japp.crm.service.StorageService;

public abstract class AbstractController implements Serializable {
	private static final long serialVersionUID = 4439434353140699253L;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	protected Neo4jOperations template;
	@Inject
	protected Mapper dozer;
	@Inject
	protected CacheServiceFacade cacheService;
	@Inject
	protected OperatorService operatorService;
	@Inject
	protected CommunicateeService communicateeService;
	@Inject
	protected IndicatorService indicatorService;
	@Inject
	protected IndicatorValueService indicatorValueService;
	@Inject
	protected CustomerService customerService;
	@Inject
	protected StorageService storageService;
	@Inject
	protected CrmTypeRepository crmTypeRepository;

	public List<CrmType> getCrmTypes() {
		return Lists.newArrayList(crmTypeRepository.findAll());
	}

	public int getPageSize() {
		return Constant.PAGE_SIZE;
	}

	protected void addInfoMessage(String s) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, s, "Success!"));
	}

	protected void addErrorMessage(Exception e) {
		ResourceBundle msg = ResourceBundle.getBundle("messages", FacesContext
				.getCurrentInstance().getViewRoot().getLocale());
		String errorMessage = getRootErrorMessage(e);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
						"Save Unsuccessful"));
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
			addErrorMessage(e);
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
			addErrorMessage(e);
		}
	}

	protected void push(FacesMessage facesMessage) throws Exception {
		PushContext pushContext = PushContextFactory.getDefault()
				.getPushContext();
		pushContext.push("/" + getCurrentOperator().getUuid(), facesMessage);
	}

	protected String getRootErrorMessage(Exception e) {
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			return errorMessage;
		}

		Throwable t = e;
		while (t != null) {
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		return errorMessage;
	}

	public Operator getCurrentOperator() throws Exception {
		return cacheService.getCurrentOperator();
	}
}
