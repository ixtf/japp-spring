package com.hengyi.japp.report.service;

import java.util.Map;

import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.MenuModel;

import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.web.model.ReportModel;

public interface CacheService extends CommonCacheService {
	Operator getCurrentOperator() throws Exception;

	MenuModel getMenuBar() throws Exception;

	MenuModel getMenuBar(Operator operator);

	Submenu getCollectSubmenu(Map<Report, ReportModel> reportModelMap);
}
