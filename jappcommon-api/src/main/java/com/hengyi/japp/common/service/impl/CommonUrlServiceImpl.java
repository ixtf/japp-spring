package com.hengyi.japp.common.service.impl;

import java.io.Serializable;

import com.hengyi.japp.common.service.CommonUrlService;

public abstract class CommonUrlServiceImpl<ID extends Serializable> implements
		CommonUrlService<ID> {
	@Override
	public String getUpdatePath(ID id) {
		return getManagePath() + "/" + id;
	}

	@Override
	public String getNewView() {
		return getUpdateView();
	}

	@Override
	public String getUpdatePath() {
		return getManagePath() + "/{id}";
	}

	@Override
	public String getUpdateView() {
		return getViewPrefix() + "/update.jsf";
	}

	@Override
	public String getManagePath() {
		return getNewPath() + "s";
	}

	@Override
	public String getManageView() {
		return getViewPrefix() + "/list.jsf";
	}

	protected String getViewPrefix() {
		return "/faces" + getNewPath();
	}
}
