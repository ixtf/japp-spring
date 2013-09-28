package com.hengyi.japp.crm.web;

import java.util.List;

import javax.annotation.PostConstruct;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.service.ReportService;

public abstract class ReportController<T extends Report> extends
		AbstractController {
	private ReportService<T> reportService;
	private Long nodeId;
	private T report;
	private List<Indicator> indicators;
	private Indicator indicator;

	@PostConstruct
	private void init() {
		reportService = getReportService();
	}

	protected abstract ReportService<T> getReportService();

	protected abstract T newReport();

	public void save() {
		try {
			getReport().setOperator(getCurrentOperator());
			reportService.save(report, getIndicators());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void addIndicator() {
		if (!getIndicators().contains(getIndicator()))
			indicators.add(indicator);
	}

	public void removeIndicator() {
		getIndicators().remove(getIndicator());
	}

	public T getReport() {
		if (report != null)
			return report;
		if (nodeId == null)
			report = newReport();
		else
			report = reportService.findOne(nodeId);
		return report;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}

	public List<Indicator> getIndicators() {
		if (indicators == null)
			indicators = Lists.newArrayList(getReport().getIndicators());
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setReport(T report) {
		this.report = report;
	}
}
