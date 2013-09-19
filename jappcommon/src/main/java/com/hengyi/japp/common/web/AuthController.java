package com.hengyi.japp.common.web;

import java.util.Properties;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hengyi.japp.common.CommonConstant;
import com.hengyi.japp.common.CommonConstant.URL;
import com.hengyi.japp.common.CommonConstant.VIEW;
import com.hengyi.japp.common.service.CacheService;

@Controller
public class AuthController extends AbstractController {
	@Resource
	private CacheService cacheService;
	@Resource(name = "deployProperties")
	private Properties deployProperties;

	@RequestMapping(value = URL.LOGIN, method = RequestMethod.GET)
	public ModelAndView loginForm() {
		return new ModelAndView(VIEW.LOGIN);
	}

	@RequestMapping(value = URL.UNAUTHORIZED, method = RequestMethod.GET)
	public ModelAndView unauthorized() {
		return new ModelAndView(VIEW.UNAUTHORIZED);
	}

	@RequestMapping(value = URL.LOGIN, method = RequestMethod.POST)
	public void login(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			@RequestParam(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM) String password,
			@RequestParam(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM) boolean rememberMe,
			Model model) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		token.setRememberMe(rememberMe);
		if (!currentUser.isAuthenticated()) {
			try {
				currentUser.login(token);
				cacheService.setSessionData();
			} catch (UnknownAccountException e) {
				addErrorMessage(model, CommonConstant.ErrorCode.USER_NOT_EXIST,
						token.getUsername());
			} catch (IncorrectCredentialsException e) {
				addErrorMessage(model,
						CommonConstant.ErrorCode.PASSWORD_INCORRECT);
			} catch (LockedAccountException e) {
				addErrorMessage(model, CommonConstant.ErrorCode.USER_LOCKED,
						token.getUsername());
			} catch (AuthenticationException e) {
				throw new Exception(e);
			}
		}
	}

	// 退出
	@RequestMapping(value = URL.LOGOUT, method = RequestMethod.GET)
	public void logout(Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}
}
