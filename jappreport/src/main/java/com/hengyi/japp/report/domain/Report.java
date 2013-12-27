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

import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public abstract class Report extends AbstractNeo4j implements Serializable,
		Comparable<Report> {
	private static final long serialVersionUID = -115312013197219536L;
	public static final String PARAM = "PARAM";
	@Indexed(level = Level.INSTANCE)
	@NotBlank
	protected String name;
	protected boolean newWindow;
	protected String note;
	protected double sortBy;
	@RelatedTo(type = Menu.MENU_REPORT, direction = Direction.INCOMING)
	@Fetch
	protected Menu menu;
	@RelatedTo(type = PARAM, elementClass = ReportParam.class)
	@Fetch
	protected Set<ReportParam> params;

	public abstract ReportType getReportType();

	public Iterable<ReportParam> getParams() {
		if (params == null)
			params = Sets.newHashSet();
		return params;
	}

	public void setParams(Iterable<ReportParam> params) {
		this.params = Sets.newHashSet(params);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = StringUtils.trim(note);
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public double getSortBy() {
		return sortBy;
	}

	public void setSortBy(double sortBy) {
		this.sortBy = sortBy;
	}

	public boolean isNewWindow() {
//		if (newWindow == null)
//			newWindow = false;
		return newWindow;
	}

	public void setNewWindow(boolean newWindow) {
		this.newWindow = newWindow;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int compareTo(Report o) {
		return Double.compare(getSortBy(), o.getSortBy());
	}
}
