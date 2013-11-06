package com.hengyi.japp.report.web.model;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;

public class FavoriteReportModel implements Serializable {
	private static final long serialVersionUID = 4042031106872493370L;
	private final Menu menu;
	private final List<Report> reports;

	public FavoriteReportModel(Menu menu, Iterable<Report> reports) {
		super();
		this.menu = menu;
		this.reports = Lists.newArrayList(Sets.newHashSet(reports));
	}

	public Menu getMenu() {
		return menu;
	}

	public List<Report> getReports() {
		return reports;
	}
}
