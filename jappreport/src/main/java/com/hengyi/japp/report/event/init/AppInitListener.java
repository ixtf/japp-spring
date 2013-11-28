package com.hengyi.japp.report.event.init;

import java.io.File;
import java.util.Collections;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import jxl.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.google.common.collect.Sets;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.ReportParam;
import com.hengyi.japp.report.domain.Role;
import com.hengyi.japp.report.domain.finereport.FineReport;
import com.hengyi.japp.report.service.MenuService;
import com.hengyi.japp.report.service.ReportFactory;
import com.hengyi.japp.report.service.ReportService;
import com.hengyi.japp.report.service.RoleService;

@Named
@Singleton
@Transactional
@SuppressWarnings("unchecked")
public class AppInitListener implements ApplicationListener<AppInitEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	private ReportFactory reportFactory;
	@Inject
	private MenuService menuService;
	@Inject
	private RoleService roleService;

	@Override
	public void onApplicationEvent(AppInitEvent event) {
		if (roleService.count() > 0)
			return;
		try {
			log.info("=============开始初始化=============");
			initRole();
			initCpts();
			log.info("=============初始化完成=============");
		} catch (Exception e) {
			log.error("=============初始化失败=============", e);
		}
	}

	private void initRole() throws Exception {
		Role role = new Role();
		role.setName("superUser");
		roleService.save(role, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
	}

	private void initCpts() throws Exception {
		ReportService<FineReport> reportService = reportFactory
				.reportService(FineReport.class);
		Menu menu = new Menu();
		menu.setName("报表分析");
		menuService.save(menu, null);
		File file = ResourceUtils.getFile("classpath:cpts.xls");
		jxl.Workbook workbook = Workbook.getWorkbook(file);
		jxl.Sheet sheet = workbook.getSheet(0);
		for (int i = 1; i < sheet.getRows(); i++) {
			String cpt = sheet.getCell(0, i).getContents();
			String name = sheet.getCell(1, i).getContents();
			FineReport report = new FineReport();
			report.setName(name);
			ReportParam param = new ReportParam();
			param.setKey("reportlet");
			param.setValue(cpt);
			Set<ReportParam> params = Sets.newHashSet(param);
			reportService.save(report, menu, params);
		}
	}
}
