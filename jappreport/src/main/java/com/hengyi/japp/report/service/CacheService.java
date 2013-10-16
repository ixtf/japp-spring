package com.hengyi.japp.report.service;

import java.io.Serializable;

import org.primefaces.model.MenuModel;

import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;

public interface CacheService extends CommonCacheService, Serializable {
	Operator getCurrentOperator() throws Exception;

	Report findOneReport(Long nodeId);

	MenuModel getMenuBar();
}
