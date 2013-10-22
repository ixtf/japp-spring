package com.hengyi.japp.report.domain;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class Role extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 1896078677101981112L;
	public static final String ROLE_REPORT = "ROLE_REPORT";
	public static final String ROLE_MENU = "ROLE_MENU";
	@Indexed(unique = true)
	private String name;
	private String note;
	@RelatedTo(type = ROLE_MENU, elementClass = Menu.class)
	private Set<Menu> menus;
	@RelatedTo(type = ROLE_REPORT)
	private Set<Report> reports;

	@NotBlank
	public String getName() {
		return name;
	}

	public String getNote() {
		return note;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public void setNote(String note) {
		this.note = StringUtils.trim(note);
	}

	public Iterable<Menu> getMenus() {
		if (menus == null)
			menus = Sets.newHashSet();
		return menus;
	}

	public Iterable<Menu> getMenus(Neo4jOperations template) {
		return template.fetch(getMenus());
	}

	public Iterable<Report> getReports() {
		if (reports == null)
			reports = Sets.newHashSet();
		return reports;
	}

	public Iterable<Report> getReports(Neo4jOperations template) {
		return template.fetch(getReports());
	}

	public void setMenus(Iterable<Menu> menus) {
		this.menus = Sets.newHashSet(menus);
	}

	public void setReports(Iterable<? extends Report> reports) {
		this.reports = Sets.newHashSet(reports);
	}

	@Override
	public String toString() {
		return getName();
	}
}
