package com.hengyi.japp.report.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.common.web.FacesAccessor;
import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.repository.MenuRepository;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.exception.ParentMenuIsSelfException;
import com.hengyi.japp.report.service.MenuService;

@Named("menuService")
@Transactional
@SuppressWarnings("unchecked")
public class MenuServiceImpl extends AbstractCommonCrudNeo4jService<Menu>
		implements MenuService {
	@Resource
	protected Neo4jOperations template;
	@Resource
	private MenuRepository menuRepository;
	@Resource
	private ReportRepository reportRepository;

	@Override
	public String getNewPath() {
		return "/admin/menu";
	}

	@Override
	public <R extends Repository<Menu, Long>> R getRepository() {
		return (R) menuRepository;
	}

	@Override
	public List<Menu> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(menuRepository.findAllByQuery(
				Menu.class.getSimpleName(), "name", nameSearch));
	}

	@Override
	public void save(Menu menu, Menu parent) throws Exception {
		if (menu.equals(parent))
			throw new ParentMenuIsSelfException();
		menu.setParent(parent);
		save(menu);
	}

	@Override
	public MenuModel getMenuBar() {
		MenuModel menuBar = new DefaultMenuModel();
		MenuItem menuItem = new MenuItem();
		menuItem.setValue("首页");
		menuItem.setIcon("ui-icon-home");
		menuItem.setUrl("/");
		menuBar.addMenuItem(menuItem);
		for (Menu menu : findAll())
			if (menu.getParent() == null)
				menuBar.addSubmenu(getSubmenu(menu));
		return menuBar;
	}

	private Submenu getSubmenu(Menu menu) {
		Submenu submenu = new Submenu();
		submenu.setLabel(menu.getName());
		for (Menu sub : menu.getSubs(template))
			submenu.getChildren().add(getSubmenu(sub));
		submenu.getChildren().addAll(
				getMenuItems(reportRepository.findAllByMenu(menu)));
		return submenu;
	}

	private Collection<MenuItem> getMenuItems(Iterable<? extends Report> reports) {
		List<MenuItem> menuItems = Lists.newArrayList();
		for (Report report : reports)
			menuItems.add(getMenuItem(report));
		return menuItems;
	}

	private MenuItem getMenuItem(Report report) {
		template.fetch(report);
		MenuItem menuItem = new MenuItem();
		menuItem.setValue(report.getName());
		menuItem.setIcon("ui-icon-document");
		menuItem.addActionListener(FacesAccessor.createMethodActionListener(
				"#{reportDisplayController.menuAction}", null,
				new Class[] { ActionEvent.class }));
		menuItem.getAttributes().put("report", report);
		return menuItem;
	}
}
