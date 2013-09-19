package com.hengyi.japp.crm.service.impl;

import static com.hengyi.japp.common.CommonConstant.ADMIN_NAME;
import static com.hengyi.japp.common.CommonConstant.ADMIN_PRINCIPAL;
import static com.hengyi.japp.common.CommonConstant.SESSION_OPERATOR;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.hengyi.japp.common.service.impl.CommonCacheServiceImpl;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Operator;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.domain.repository.CertificateRepository;
import com.hengyi.japp.crm.domain.repository.CrmTypeRepository;
import com.hengyi.japp.crm.domain.repository.IndicatorRepository;
import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.service.CacheService;
import com.hengyi.japp.crm.service.OperatorService;

@Named("cacheService")
@Singleton
public class CacheServiceImpl extends CommonCacheServiceImpl implements
		CacheService {
	@Inject
	private OperatorService operatorService;
	@Inject
	private CrmTypeRepository crmTypeRepository;
	@Inject
	private CertificateRepository certificateRepository;
	@Inject
	private IndicatorRepository indicatorRepository;

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
	private void initIndicator() {
		if (indicatorRepository.count() > 0)
			return;
		for (Indicator indicator : CustomerIndicator.getCalculateIndicators())
			indicatorRepository.save(indicator);
		for (Indicator indicator : StorageIndicator.getCalculateIndicators())
			indicatorRepository.save(indicator);
	}

	@PostConstruct
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

	@PostConstruct
	private void initCertificate() {
		if (certificateRepository.count() > 0)
			return;
		certificateRepository.save(new Certificate("组织机构代码证"));
		certificateRepository.save(new Certificate("税务登记证"));
		certificateRepository.save(new Certificate("营业执造"));
		certificateRepository.save(new Certificate("一般纳税人资格证"));
		certificateRepository.save(new Certificate("商业登记证"));
		certificateRepository.save(new Certificate("离岸账户证明"));
	}
}
