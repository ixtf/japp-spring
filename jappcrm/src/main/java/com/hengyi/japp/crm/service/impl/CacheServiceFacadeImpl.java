package com.hengyi.japp.crm.service.impl;

import static com.hengyi.japp.common.Constant.ADMIN_NAME;
import static com.hengyi.japp.common.Constant.ADMIN_PRINCIPAL;
import static com.hengyi.japp.common.Constant.SESSION_OPERATOR;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.hengyi.japp.common.service.impl.CacheServiceImpl;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Operator;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.domain.repository.CrmTypeRepository;
import com.hengyi.japp.crm.domain.repository.IndicatorRepository;
import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.service.CacheServiceFacade;
import com.hengyi.japp.crm.service.OperatorService;

@Named("cacheService")
@Singleton
public class CacheServiceFacadeImpl extends CacheServiceImpl implements
		CacheServiceFacade {
	@Inject
	private OperatorService operatorService;
	@Inject
	private CrmTypeRepository crmTypeRepository;
	@Inject
	private IndicatorRepository indicatorRepository;

	@Override
	public Operator getCurrentOperator() throws Exception {
		Operator operator = getSession(SESSION_OPERATOR, Operator.class);
		if (operator != null)
			return operator;
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated())
			return operator;
		if (ADMIN_PRINCIPAL.equals(subject.getPrincipal()))
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
		initIndicator();
		initCrmType();
	}

	private void initIndicator() {
		if (indicatorRepository.count() > 0)
			return;
		for (Indicator indicator : CustomerIndicator.getCalculateIndicators())
			indicatorRepository.save(indicator);
		for (Indicator indicator : StorageIndicator.getCalculateIndicators())
			indicatorRepository.save(indicator);
	}

	private void initCrmType() {
		if (crmTypeRepository.count() > 0)
			return;
		crmTypeRepository.save(new CrmType("国有企业"));
		crmTypeRepository.save(new CrmType("集体企业"));
		crmTypeRepository.save(new CrmType("有限责任公司"));
		crmTypeRepository.save(new CrmType("股份有限公司"));
		crmTypeRepository.save(new CrmType("私营企业"));
		crmTypeRepository.save(new CrmType("中外合资企业"));
		crmTypeRepository.save(new CrmType("外商投资企业"));
	}
}
