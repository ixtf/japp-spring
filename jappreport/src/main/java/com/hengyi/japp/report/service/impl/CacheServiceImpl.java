package com.hengyi.japp.report.service.impl;

import static com.hengyi.japp.common.CommonConstant.ADMIN_NAME;
import static com.hengyi.japp.common.CommonConstant.ADMIN_PRINCIPAL;
import static com.hengyi.japp.common.CommonConstant.SESSION_OPERATOR;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.hengyi.japp.common.service.AbstractCommonCacheService;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.service.CacheService;
import com.hengyi.japp.report.service.OperatorService;

@Named("cacheService")
@Singleton
public class CacheServiceImpl extends AbstractCommonCacheService implements
		CacheService {
	@Inject
	private OperatorService operatorService;
	@Resource
	private ReportRepository reportRepository;

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
			return "cupertino";
		}
		return operator == null ? "cupertino" : operator.getTheme();
	}
}
