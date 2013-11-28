package com.hengyi.japp.report.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.jasig.cas.client.validation.AbstractUrlBasedTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hengyi.japp.common.shiro.BaseSsoRealm;
import com.hengyi.japp.report.MyUtil;

public class SsoRealm extends BaseSsoRealm {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	protected TicketValidator ensureTicketValidator() {
		TicketValidator result = super.ensureTicketValidator();
		if (result instanceof AbstractUrlBasedTicketValidator)
			((AbstractUrlBasedTicketValidator) result).setEncoding("UTF-8");
		return result;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		try {
			return MyUtil.doGetAuthorizationInfo(principals);
		} catch (Exception e) {
			log.error(principals + " 获取权限出错！", e);
			return super.doGetAuthorizationInfo(principals);
		}
	}
}