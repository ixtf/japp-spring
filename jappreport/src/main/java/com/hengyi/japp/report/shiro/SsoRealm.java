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
import com.hengyi.japp.common.shiro.BaseSsoRealm;
import com.hengyi.japp.report.service.OperatorService;

public class SsoRealm extends BaseSsoRealm {
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
				// 限制SSO登入,以后删除
				if (operatorService.findOne(user.getUuid()) == null)
					throw new AuthenticationException("user not exist!");
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