package com.hengyi.japp.trans.web;

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

import com.google.common.collect.Lists;
import com.hengyi.japp.common.web.CommonAbstractController;
import com.hengyi.japp.trans.domain.Operator;
import com.hengyi.japp.trans.domain.PackType;
import com.hengyi.japp.trans.domain.TransType;
import com.hengyi.japp.trans.domain.repository.PackTypeRepository;
import com.hengyi.japp.trans.domain.repository.TransTypeRepository;
import com.hengyi.japp.trans.service.CacheService;
import com.hengyi.japp.trans.service.OperatorService;
import com.hengyi.japp.trans.service.YSDeliveryService;

public abstract class AbstractController extends CommonAbstractController {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	protected CacheService cacheService;
	@Inject
	protected OperatorService operatorService;
	@Inject
	protected YSDeliveryService ysDeliveryService;
	@Resource
	protected PackTypeRepository packTypeRepository;
	@Resource
	protected TransTypeRepository transTypeRepository;

	public List<PackType> getAllPackTypes() {
		return Lists.newArrayList(packTypeRepository.findAll());
	}

	public List<TransType> getAllTransType() {
		return Lists.newArrayList(transTypeRepository.findAll());
	}

	public String getRemoteAddr() {
		return ((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getRemoteAddr();
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

	protected void push(FacesMessage facesMessage) throws Exception {
		PushContext pushContext = PushContextFactory.getDefault()
				.getPushContext();
		pushContext.push("/" + getCurrentOperator().getUuid(), facesMessage);
	}
}
