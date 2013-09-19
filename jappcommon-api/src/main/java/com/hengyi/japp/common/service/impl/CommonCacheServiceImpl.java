package com.hengyi.japp.common.service.impl;

import static com.hengyi.japp.common.CommonConstant.DEFAULT_THEME;

import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.hengyi.japp.common.CommonUrlUtil;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.data.Theme;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.sap.Constant;
import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.common.shiro.ShiroUtil;

public abstract class CommonCacheServiceImpl implements CommonCacheService {
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
		Object principal = ShiroUtil.getPrincipal();
		if (principal == null)
			principal = SecurityUtils.getSubject().getPrincipal();
		return principal;
	}

	@Override
	public final UserDTO getUser() throws Exception {
		return ShiroUtil.getUser();
	}

	@Override
	public final <T> T getSession(Object key) {
		return ShiroUtil.getSession(key);
	}

	@Override
	public final void setSession(Object key, Object value) {
		ShiroUtil.setSession(key, value);
	}

	@Override
	public boolean isAuthenticated() {
		return ShiroUtil.isAuthenticated();
	}

	@Override
	public boolean isAdmin() {
		return SecurityUtils.getSubject().hasRole(Constant.ADMIN_PRINCIPAL);
	}

	// private final AsyncEventBus asyncEventBus;
	//
	// public CacheServiceImpl() {
	// super();
	// asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());
	// }
	//
	// @Override
	// public AsyncEventBus getAsyncEventBus() {
	// return asyncEventBus;
	// }

	// @Override
	// public String getHomePath() {
	// return CommonUrlUtil.getHomePath();
	// }
	//
	// @Override
	// public String getHomeView() {
	// return CommonUrlUtil.getHomeView();
	// }
	//
	// @Override
	// public String getLoginPath() {
	// return CommonUrlUtil.getLoginPath();
	// }
	//
	// @Override
	// public String getLoginView() {
	// return CommonUrlUtil.getLoginView();
	// }
	//
	// @Override
	// public String getLogoutPath() {
	// return CommonUrlUtil.getLogoutPath();
	// }
	//
	// @Override
	// public String getThemePath() {
	// return CommonUrlUtil.getThemePath();
	// }
	//
	// @Override
	// public String getThemeView() {
	// return CommonUrlUtil.getThemeView();
	// }
	//
	// @Override
	// public String getAdminHomePath() {
	// return CommonUrlUtil.getAdminHomePath();
	// }
	//
	// @Override
	// public String getAdminHomeView() {
	// return CommonUrlUtil.getAdminHomeView();
	// }
	//
	// @Override
	// public String getUnauthorizedPath() {
	// return CommonUrlUtil.getUnauthorizedPath();
	// }
	//
	// @Override
	// public String getBugNewPath() {
	// return CommonUrlUtil.getBugNewPath();
	// }
	//
	// @Override
	// public String getBugUpdateView() {
	// return CommonUrlUtil.getBugUpdateView();
	// }
	//
	// @Override
	// public String getBugsPath() {
	// return CommonUrlUtil.getBugsPath();
	// }
	//
	// @Override
	// public String getBugsView() {
	// return CommonUrlUtil.getBugsView();
	// }
}
