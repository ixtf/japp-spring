package com.hengyi.japp.report.domain;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.Indexed.Level;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class ReportMenu extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -2944108349567642060L;
	public static final String REPORTMENU = "REPORTMENU";
	public static final String REPORTMENUITEM = "REPORTMENUITEM";
	@Indexed(unique = true, level = Level.INSTANCE)
	protected String name;
	@RelatedTo(type = REPORTMENU, direction = Direction.INCOMING)
	protected ReportMenu parentReportMenus;
	@RelatedTo(type = REPORTMENU, elementClass = ReportMenu.class)
	@Fetch
	protected Set<ReportMenu> subReportMenus;
	@RelatedTo(type = REPORTMENUITEM, elementClass = Report.class)
	@Fetch
	protected Set<Report> reports;

	public boolean isTop() {
		return getParentReportMenus() == null;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public ReportMenu getParentReportMenus() {
		return parentReportMenus;
	}

	public Set<ReportMenu> getSubReportMenus() {
		return subReportMenus;
	}

	public Set<Report> getReports() {
		return reports;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public void setParentReportMenus(ReportMenu parentReportMenus) {
		this.parentReportMenus = parentReportMenus;
	}

	public void setSubReportMenus(Set<ReportMenu> subReportMenus) {
		this.subReportMenus = subReportMenus;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	@Override
	public String toString() {
		return getName();
	}
}
