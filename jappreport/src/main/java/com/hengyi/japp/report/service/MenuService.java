package com.hengyi.japp.report.service;

import java.util.List;

import org.primefaces.model.MenuModel;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.report.domain.Menu;

public interface MenuService extends CommonCrudNeo4jService<Menu> {
	void save(Menu menu, Menu parent) throws Exception;

	List<Menu> findAllByQuery(String nameSearch) throws Exception;

	MenuModel getMenuBar();
}
