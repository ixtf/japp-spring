package com.hengyi.japp.common.service.impl;

import static com.hengyi.japp.common.Constant.DEFAULT_THEME;

import java.util.List;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.data.Theme;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.service.CacheService;
import com.hengyi.japp.common.shiro.ShiroUtil;

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
		return ShiroUtil.getPrincipalType();
	}

	@Override
	public final Object getPrincipal() throws Exception {
		return ShiroUtil.getPrincipal();
	}

	@Override
	public final UserDTO getUser() throws Exception {
		return ShiroUtil.getUser();
	}

	@Override
	public final <T> T getSession(Object key, Class<T> clazz) throws Exception {
		return ShiroUtil.getSession(key, clazz);
	}

	@Override
	public final void setSession(Object key, Object value) throws Exception {
		ShiroUtil.setSession(key, value);
	}
}
