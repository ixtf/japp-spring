package com.hengyi.japp.report.service.impl;

import static com.hengyi.japp.common.CommonConstant.ADMIN_NAME;
import static com.hengyi.japp.common.CommonConstant.ADMIN_PRINCIPAL;
import static com.hengyi.japp.common.CommonConstant.SESSION_OPERATOR;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.hengyi.japp.common.service.impl.CommonCacheServiceImpl;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.service.CacheService;
import com.hengyi.japp.report.service.OperatorService;
import com.hengyi.japp.report.web.AbstractController;

@Named("cacheService")
@Singleton
public class CacheServiceImpl extends CommonCacheServiceImpl implements
		CacheService {
	private static final long serialVersionUID = 443624906271670857L;
	@Inject
	private OperatorService operatorService;
	@Resource
	private ReportRepository reportRepository;
	private Cache<Long, Report> reportCache;

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
			return super.getTheme();
		}
		return operator == null ? super.getTheme() : operator.getTheme();
	}

	@Override
	public Report findOneReport(Long nodeId) {
		return reportCache.getIfPresent(nodeId);
	}

	@Override
	public MenuModel getMenuBar() {
		MenuModel menuBar = new DefaultMenuModel();
		for (Report report : reportRepository.findAll()) {
			MenuItem menuItem = new MenuItem();
			menuItem.setValue(report.getName());
			menuItem.setTitle(String.valueOf(report.getNodeId()));
			menuItem.setIcon("ui-icon-document");
			menuItem.setAjax(false);
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void processAction(ActionEvent event)
						throws AbortProcessingException {
					AbstractController.redirect("/reports/"
							+ ((MenuItem) event.getSource()).getTitle()
							+ "/display");
				}
			});
			menuBar.addMenuItem(menuItem);
		}
		return menuBar;
	}

	@PostConstruct
	private void init() {
		reportCache = CacheBuilder.newBuilder().build();
		for (Report report : reportRepository.findAll())
			reportCache.put(report.getNodeId(), report);
	}
}
