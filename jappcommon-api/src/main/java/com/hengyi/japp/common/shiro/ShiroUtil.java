package com.hengyi.japp.common.shiro;

import static com.hengyi.japp.common.Constant.SESSION_PRINCIPAL;
import static com.hengyi.japp.common.Constant.SESSION_PRINCIPALTYPE;
import static com.hengyi.japp.common.Constant.SESSION_USER;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.ws.SoapService;

@SuppressWarnings("unchecked")
public final class ShiroUtil {
	public static <T> T getSession(Object key, Class<T> value) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return (T) session.getAttribute(key);
	}

	public static void setSession(Object key, Object value) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.setAttribute(key, value);
	}

	protected static void setSession(PrincipalType principalType,
			String principal, SoapService jappCommonSoapClient)
			throws Exception {
		UserDTO user = jappCommonSoapClient.findOneUser(principalType,
				principal);
		setSession(SESSION_PRINCIPALTYPE, principalType);
		setSession(SESSION_PRINCIPAL, principal);
		setSession(SESSION_USER, user);
	}

	public static PrincipalType getPrincipalType() throws Exception {
		return getSession(SESSION_PRINCIPALTYPE, PrincipalType.class);
	}

	public static Object getPrincipal() throws Exception {
		return getSession(SESSION_PRINCIPAL, Object.class);
	}

	public static UserDTO getUser() throws Exception {
		return getSession(SESSION_USER, UserDTO.class);
	}
}
