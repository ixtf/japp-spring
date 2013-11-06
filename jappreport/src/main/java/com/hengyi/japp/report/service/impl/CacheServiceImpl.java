package com.hengyi.japp.report.service.impl;

import static com.hengyi.japp.common.CommonConstant.ADMIN_NAME;
import static com.hengyi.japp.common.CommonConstant.ADMIN_PRINCIPAL;
import static com.hengyi.japp.common.CommonConstant.SESSION_OPERATOR;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.shiro.SecurityUtils;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.hengyi.japp.common.service.AbstractCommonCacheService;
import com.hengyi.japp.common.web.FacesAccessor;
import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.CacheService;
import com.hengyi.japp.report.service.MenuService;
import com.hengyi.japp.report.service.OperatorService;
import com.hengyi.japp.report.web.model.ReportModel;

@Named("cacheService")
@Singleton
public class CacheServiceImpl extends AbstractCommonCacheService implements
		CacheService {
	@Resource
	private Neo4jOperations template;
	@Resource
	private EventBus eventBus;
	@Inject
	private MenuService menuService;
	@Inject
	private OperatorService operatorService;

	@Override
	public MenuModel getMenuBar() throws Exception {
		return getMenuBar(getCurrentOperator());
	}

	@Override
	public MenuModel getMenuBar(Operator operator) {
		// TODO 暂时解决当点登入
		if (!SecurityUtils.getSubject().hasRole("superUser"))
			return null;
		MenuModel menuBar = new DefaultMenuModel();
		menuBar.addMenuItem(MyUtil.getHomeMenuItem());
		for (Menu menu : menuService.findAllTopMenu())
			menuBar.addSubmenu(getSubmenu(menu));
		menuBar.addMenuItem(MyUtil.getLogoutMenuItem());
		return menuBar;
	}

	private Submenu getSubmenu(Menu menu) {
		Submenu submenu = new Submenu();
		submenu.setLabel(menu.getName());
		for (Menu sub : menu.getSubs(template))
			submenu.getChildren().add(getSubmenu(sub));
		submenu.getChildren().addAll(
				getMenuItems(menuService.findAllReport(menu)));
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
		menuItem.getAttributes().put("report", report);
		menuItem.addActionListener(FacesAccessor.createMethodActionListener(
				"#{reportDisplayController.menuAction}", null,
				new Class[] { ActionEvent.class }));
		return menuItem;
	}

	@Override
	public Submenu getCollectSubmenu(Map<Report, ReportModel> reportModelMap) {
		Submenu result = new Submenu();
		for (Report report : reportModelMap.keySet())
			result.getChildren().add(getCollectMenuItem(report));
		return result;
	}

	private MenuItem getCollectMenuItem(Report report) {
		MenuItem menuItem = new MenuItem();
		menuItem.setValue(report.getName());
		menuItem.setIcon("ui-icon-document");
		menuItem.getAttributes().put("report", report);
		menuItem.addActionListener(FacesAccessor.createMethodActionListener(
				"#{homeController.collectMenuAction}", null,
				new Class[] { ActionEvent.class }));
		return menuItem;
	}

	@Override
	public Operator getCurrentOperator() throws Exception {
		Operator operator = getSession(SESSION_OPERATOR);
		if (operator != null)
			return operator;
		if (!isAuthenticated())
			return operator;
		if (ADMIN_PRINCIPAL.equals(getPrincipal()))
			operator = new Operator(ADMIN_PRINCIPAL, ADMIN_NAME);
		else
			operator = operatorService.findOne(getUser());
		setSession(SESSION_OPERATOR, operator);
		return operator;
	}

	@Override
	public String getTheme() {
		Operator operator;
		try {
			operator = getCurrentOperator();
		} catch (Exception e) {
			return "cupertino";
		}
		return operator == null ? "cupertino" : operator.getTheme();
	}

	@PostConstruct
	private void init() {
		eventBus.register(this);
	}
}
