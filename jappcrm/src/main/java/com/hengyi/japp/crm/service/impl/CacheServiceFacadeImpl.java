package com.hengyi.japp.crm.service.impl;

import static com.hengyi.japp.common.Constant.ADMIN_NAME;
import static com.hengyi.japp.common.Constant.ADMIN_PRINCIPAL;
import static com.hengyi.japp.common.Constant.SESSION_OPERATOR;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.hengyi.japp.common.service.impl.CacheServiceImpl;
import com.hengyi.japp.crm.domain.Operator;
import com.hengyi.japp.crm.service.CacheServiceFacade;
import com.hengyi.japp.crm.service.OperatorService;

@Named("cacheService")
@Singleton
public class CacheServiceFacadeImpl extends CacheServiceImpl implements
		CacheServiceFacade {
	@Inject
	private OperatorService operatorService;

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
		if (operator == null)
			return super.getTheme();
		else
			return operator.getTheme();
	}
}
