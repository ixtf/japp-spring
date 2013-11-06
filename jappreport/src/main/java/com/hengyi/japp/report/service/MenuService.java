package com.hengyi.japp.report.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;

public interface MenuService extends CommonCrudNeo4jService<Menu> {
	void save(Menu menu, Menu parent) throws Exception;

	List<Menu> findAllTopMenu();

	List<Menu> findAllByQuery(String nameSearch) throws Exception;

	List<Report> findAllReport(Menu menu);

	List<Report> findAllReport(Iterable<Menu> menus);
}
