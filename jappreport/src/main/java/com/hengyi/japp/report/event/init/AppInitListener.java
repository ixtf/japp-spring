package com.hengyi.japp.report.event.init;

import java.io.File;
import java.util.Set;

import javax.annotation.Resource;
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
import com.hengyi.japp.report.domain.finereport.FineReport;
import com.hengyi.japp.report.domain.repository.FineReportRepository;
import com.hengyi.japp.report.domain.repository.MenuRepository;

@Named
@Singleton
@Transactional
public class AppInitListener implements ApplicationListener<AppInitEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private FineReportRepository fineReportRepository;
	@Resource
	private MenuRepository menuRepository;

	@Override
	public void onApplicationEvent(AppInitEvent event) {
		if (fineReportRepository.count() > 0)
			return;
		try {
			log.info("=============开始初始化=============");
			initCpts();
			log.info("=============开始完成=============");
		} catch (Exception e) {
			log.error("=============初始化失败=============", e);
		}
	}

	private void initCpts() throws Exception {
		Menu menu = new Menu();
		menu.setName("报表分析");
		menuRepository.save(menu);
		Set<FineReport> reports = Sets.newHashSet();
		File file = ResourceUtils.getFile("classpath:cpts.xls");
		jxl.Workbook workbook = Workbook.getWorkbook(file);
		jxl.Sheet sheet = workbook.getSheet(0);
		for (int i = 1; i < sheet.getRows(); i++) {
			String cpt = sheet.getCell(0, i).getContents();
			String name = sheet.getCell(1, i).getContents();
			FineReport report = new FineReport();
			report.setMenu(menu);
			report.setCpt(cpt);
			report.setName(name);

			reports.add(report);
		}
		fineReportRepository.save(reports);
	}

	// private void initCpts() throws Exception {
	// Menu menu = new Menu();
	// menu.setName("报表分析");
	// Set<FineReport> reports = Sets.newHashSet();
	// File file = ResourceUtils.getFile("classpath:cpts.xls");
	// jxl.Workbook workbook = Workbook.getWorkbook(file);
	// jxl.Sheet sheet = workbook.getSheet(0);
	// for (int i = 1; i < sheet.getRows(); i++) {
	// String cpt = sheet.getCell(0, i).getContents();
	// String name = sheet.getCell(1, i).getContents();
	// FineReport report = new FineReport();
	// report.setCpt(cpt);
	// report.setName(name);
	// reports.add(report);
	// }
	// fineReportRepository.save(reports);
	// menu.setReports(reports);
	// menuRepository.save(menu);
	// }
}
