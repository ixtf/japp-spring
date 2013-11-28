package com.hengyi.japp.report.service;

import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.report.domain.Operator;

public interface CacheService extends CommonCacheService {
	Operator getCurrentOperator() throws Exception;

	// MenuModel getMenuBar() throws Exception;
	//
	// MenuModel getMenuBar(Operator operator);
	//
	// Submenu getCollectSubmenu(Map<Report, ReportModel> reportModelMap);
}
