package com.hengyi.japp.report.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.ReportParam;
import com.hengyi.japp.report.domain.repository.ReportParamRepository;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.service.ReportService;

public abstract class ReportServiceImpl<T extends Report> extends
		AbstractCommonCrudNeo4jService<T> implements ReportService<T> {
	@Resource
	private ReportRepository reportRepository;
	@Resource
	private ReportParamRepository reportParamRepository;

	@Override
	public List<Report> findAllByMenu(Menu menu) {
		return Lists.newArrayList(reportRepository.findAllByMenu(menu));
	}

	@Override
	public List<Report> findAllByMenu(Iterable<Menu> menus) {
		return Lists.newArrayList(reportRepository.findAllByMenu(menus));
	}

	@Override
	public void save(T report, Menu menu, Iterable<ReportParam> params)
			throws Exception {
		reportParamRepository.save(params);
		report.setMenu(menu);
		report.setParams(params);
		reportRepository.save(report);
	}

	@Override
	public final String getUrl(T report) {
		StringBuilder sb = new StringBuilder(getBaseUrl(report));
		for (ReportParam param : report.getParams())
			sb.append(param.getKey()).append("=").append(param.getValue())
					.append("&");
		// sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	@Override
	public void delete(T report) throws Exception {
		reportRepository.delete(report);
	}

	protected abstract String getBaseUrl(T report);
}
