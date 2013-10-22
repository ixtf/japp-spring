package com.hengyi.japp.report.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.service.ReportService;

public abstract class ReportServiceImpl<T extends Report> extends
		AbstractCommonCrudNeo4jService<T> implements ReportService<T> {
	@Resource
	private ReportRepository reportRepository;

	@Override
	public List<Report> findAllByMenu(Menu menu) {
		return Lists.newArrayList(reportRepository.findAllByMenu(menu));
	}

	@Override
	public List<Report> findAllByMenu(Iterable<Menu> menus) {
		return Lists.newArrayList(reportRepository.findAllByMenu(menus));
	}
}
