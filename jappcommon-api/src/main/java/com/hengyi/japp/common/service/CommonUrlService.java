package com.hengyi.japp.common.service;

import java.io.Serializable;

public interface CommonUrlService<ID extends Serializable> {
	String getNewPath();

	String getUpdatePath(ID id);

	String getUpdateView();

	String getManagePath();

	String getManageView();
}
