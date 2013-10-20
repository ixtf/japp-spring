package com.hengyi.japp.report.shiro;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.shiro.BaseRealm;
import com.hengyi.japp.report.service.OperatorService;

public class Realm extends BaseRealm {
	@Resource(name = "jappCommonSoapClient")
	private com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;
	@Inject
	private OperatorService operatorService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		AuthenticationInfo info = super.doGetAuthenticationInfo(token);
		try {
			if (info != null) {
				UserDTO user = jappCommonSoapClient.findOneUser(
						PrincipalType.SSO, info.getPrincipals()
								.getPrimaryPrincipal().toString());
				// 手动登入,创建用户，以后删除
				operatorService.findOne(user);
			}
			return info;
		} catch (Exception e) {
			throw new AuthenticationException(e);
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}
}