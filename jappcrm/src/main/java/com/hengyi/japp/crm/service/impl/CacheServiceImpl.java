package com.hengyi.japp.crm.service.impl;

import static com.hengyi.japp.common.CommonConstant.ADMIN_NAME;
import static com.hengyi.japp.common.CommonConstant.ADMIN_PRINCIPAL;
import static com.hengyi.japp.common.CommonConstant.SESSION_OPERATOR;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.hengyi.japp.common.service.AbstractCommonCacheService;
import com.hengyi.japp.crm.Constant;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.CorporationType;
import com.hengyi.japp.crm.domain.Operator;
import com.hengyi.japp.crm.domain.repository.CertificateRepository;
import com.hengyi.japp.crm.domain.repository.CorporationTypeRepository;
import com.hengyi.japp.crm.event.CertificateDeleteEvent;
import com.hengyi.japp.crm.event.CertificateUpdateEvent;
import com.hengyi.japp.crm.event.CorporationTypeDeleteEvent;
import com.hengyi.japp.crm.event.CorporationTypeUpdateEvent;
import com.hengyi.japp.crm.service.CacheService;
import com.hengyi.japp.crm.service.OperatorService;

@Named("cacheService")
@Singleton
public class CacheServiceImpl extends AbstractCommonCacheService implements
		CacheService {
	@Inject
	private EventBus eventBus;
	@Inject
	private OperatorService operatorService;
	@Inject
	private CorporationTypeRepository corporationTypeRepository;
	@Inject
	private CertificateRepository certificateRepository;
	private Constant.Url _url = new Constant.Url();
	private List<CorporationType> corporationTypes;
	private List<Certificate> certificates;

	@Override
	public List<CorporationType> getAllCorporationTypes() {
		return corporationTypes;
	}

	@Override
	public List<Certificate> getAllCertificates() {
		return certificates;
	}

	public Constant.Url getUrl() {
		return _url;
	}

	@Override
	public Operator getCurrentOperator() throws Exception {
		Operator operator = getSession(SESSION_OPERATOR);
		if (operator != null)
			return operator;
		if (!isAuthenticated())
			return operator;
		if (ADMIN_PRINCIPAL.equals(getPrincipal()))
			operator = new Operator(ADMIN_PRINCIPAL, ADMIN_NAME);
		else
			operator = operatorService.findOne(getUser());
		setSession(SESSION_OPERATOR, operator);
		return operator;
	}

	@Override
	public String getTheme() {
		Operator operator;
		try {
			operator = getCurrentOperator();
		} catch (Exception e) {
			return super.getTheme();
		}
		return operator == null ? super.getTheme() : operator.getTheme();
	}

	@PostConstruct
	private void init() {
		eventBus.register(this);
		corporationTypes = ImmutableList.copyOf(corporationTypeRepository
				.findAll());
		certificates = ImmutableList.copyOf(certificateRepository.findAll());
	}

	@Subscribe
	public void CorporationTypeUpdateEvent(CorporationTypeUpdateEvent event) {
		corporationTypes = ImmutableList.copyOf(corporationTypeRepository
				.findAll());
	}

	@Subscribe
	public void CorporationTypeDeleteEvent(CorporationTypeDeleteEvent event) {
		corporationTypes = ImmutableList.copyOf(corporationTypeRepository
				.findAll());
	}

	@Subscribe
	public void CertificateUpdateEvent(CertificateUpdateEvent event) {
		certificates = ImmutableList.copyOf(certificateRepository.findAll());
	}

	@Subscribe
	public void CertificateDeleteEvent(CertificateDeleteEvent event) {
		certificates = ImmutableList.copyOf(certificateRepository.findAll());
	}
}
