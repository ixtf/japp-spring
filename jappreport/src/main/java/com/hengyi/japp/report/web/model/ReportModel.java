package com.hengyi.japp.report.web.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.web.context.ContextLoader;

import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.ReportServiceFactory;

public class ReportModel implements Serializable {
	private static final long serialVersionUID = -3028260849248683610L;
	private final Report report;
	private String url;

	public ReportModel(Report report) {
		this.report = report;
		ReportServiceFactory reportServiceFactory = ContextLoader
				.getCurrentWebApplicationContext().getBean(
						ReportServiceFactory.class);
		url = reportServiceFactory.get(getReport()).getUrl(getReport());
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
