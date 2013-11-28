package com.hengyi.japp.report.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hengyi.japp.common.shiro.BaseRealm;
import com.hengyi.japp.report.MyUtil;

public class Realm extends BaseRealm {
	private final Logger log = LoggerFactory.getLogger(getClass());

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