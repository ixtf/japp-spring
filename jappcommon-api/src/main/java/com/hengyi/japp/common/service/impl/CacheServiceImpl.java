package com.hengyi.japp.common.service.impl;

import static com.hengyi.japp.common.Constant.DEFAULT_THEME;
import static com.hengyi.japp.common.Constant.SESSION_PRINCIPAL;
import static com.hengyi.japp.common.Constant.SESSION_PRINCIPALTYPE;
import static com.hengyi.japp.common.Constant.SESSION_USER;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.data.Theme;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.service.CacheService;

@SuppressWarnings("unchecked")
public abstract class CacheServiceImpl implements CacheService {
	@Override
	public String getTheme() {
		return DEFAULT_THEME.getName();
	}

	private List<Theme> themes;

	@Override
	public final List<Theme> getThemes() {
		return themes != null ? themes : getThemesFromCommon();
	}

	private synchronized List<Theme> getThemesFromCommon() {
		themes = Theme.getDefault();
		return themes;
	}

	@Override
	public final PrincipalType getPrincipalType() throws Exception {
		return getSession(SESSION_PRINCIPALTYPE, PrincipalType.class);
	}

	@Override
	public final Object getPrincipal() throws Exception {
		return getSession(SESSION_PRINCIPAL, Object.class);
	}

	@Override
	public final UserDTO getUser() throws Exception {
		return getSession(SESSION_USER, UserDTO.class);
	}

	@Override
	public final <T> T getSession(Object key, Class<T> value) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return (T) session.getAttribute(key);
	}

	@Override
	public final void setSession(Object key, Object value) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.setAttribute(key, value);
	}
}
