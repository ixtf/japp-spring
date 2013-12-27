package com.hengyi.japp.common.service;

public abstract class AbstractUrlService {
	public abstract String getNewPath();

	public String getUpdatePath(Object id) {
		return getManagePath() + "/" + id;
	}

	public String getNewView() {
		return getUpdateView();
	}

	public String getUpdatePath() {
		return getManagePath() + "/{id}";
	}

	public String getUpdateView() {
		return getViewPrefix() + "/update.jsf";
	}

	public String getManagePath() {
		return getNewPath() + "s";
	}

	public String getManageView() {
		return getViewPrefix() + "/list.jsf";
	}

	protected String getViewPrefix() {
		return "/faces" + getNewPath();
	}
}
