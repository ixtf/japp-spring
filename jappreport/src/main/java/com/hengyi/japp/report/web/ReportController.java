package com.hengyi.japp.report.web;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.ReportParam;
import com.hengyi.japp.report.domain.repository.ReportParamRepository;
import com.hengyi.japp.report.service.ReportService;

public abstract class ReportController<T extends Report> extends
		AbstractController {
	@Resource
	private ReportParamRepository reportParamRepository;
	private Long nodeId;
	private T report;
	private ReportService<T> reportService;
	@NotNull
	private Menu menu;
	private List<ReportParam> params;
	private ReportParam param;

	@PostConstruct
	private void init() {
		reportService = getReportService();
	}

	protected abstract ReportService<T> getReportService();

	public void save() {
		try {
			reportService.save(getReport(), getMenu(), getParams());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void newParam() {
		param = null;
	}

	public void addParam() {
		if (!getParams().contains(getParam()))
			params.add(param);
		Collections.sort(params);
	}

	public void deleteParam() {
		getParams().remove(getParam());
	}

	public Long getNodeId() {
		return nodeId;
	}

	public T getReport() {
		if (report != null)
			return report;
		if (getNodeId() == null)
			report = reportService.newReport();
		else
			report = reportService.findOne(getNodeId());
		return report;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setReport(T report) {
		this.report = report;
	}

	public Menu getMenu() {
		if (menu == null)
			menu = getReport().getMenu();
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<ReportParam> getParams() {
		if (params == null)
			params = Lists.newArrayList(getReport().getParams());
		return params;
	}

	public void setParams(List<ReportParam> params) {
		this.params = params;
	}

	public ReportParam getParam() {
		if (param == null)
			param = new ReportParam();
		return param;
	}

	public void setParam(ReportParam param) {
		this.param = param;
	}
}
