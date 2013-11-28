package com.hengyi.japp.report.web.model;

import java.io.Serializable;
import java.util.Objects;

import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.ReportFactory;

public class ReportModel implements Serializable {
	private static final long serialVersionUID = -3028260849248683610L;
	private final Report report;
	private final String url;

	public ReportModel(Report report, ReportFactory factory) {
		this.report = report;
		this.url = factory.reportService(report).getUrl(report);
	}

	public Report getReport() {
		return report;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final ReportModel other = (ReportModel) o;
		return Objects.equals(getReport(), other.getReport());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getReport());
	}

	@Override
	public String toString() {
		return getReport().toString();
	}
}
