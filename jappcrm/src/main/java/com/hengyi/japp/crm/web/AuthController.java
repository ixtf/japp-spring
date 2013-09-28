package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.crm.service.CacheService;

@Named
@Scope("request")
// @Join(path = "/login", to = "/faces/login.jsf")
public class AuthController extends AbstractController implements Serializable {
	private static final long serialVersionUID = 3708518912737819900L;
	@Inject
	protected CacheService cacheService;
	@Resource(name = "deployProperties")
	private Properties deployProperties;
	@NotBlank
	private String username;
	@NotBlank
	private String password;

	public void login() {
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password);
			subject.login(token);
			redirect(cacheService.getHomePath());
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void logout() {
		String url = cacheService.getLoginPath();
		try {
			if (PrincipalType.SSO.equals(cacheService.getPrincipalType()))
				url = deployProperties.getProperty("casLogoutUrl");
		} catch (Exception e) {
			log.error("", e);
		}
		SecurityUtils.getSubject().logout();
		redirect(url);
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
