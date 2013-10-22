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
import com.hengyi.japp.report.Constant;

@NodeEntity
public class Operator extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -4364743082478493931L;
	public static final String OPERATOR_ROLE = "OPERATOR_ROLE";
	public static final String OPERATOR_MENU = "OPERATOR_MENU";
	public static final String OPERATOR_REPORT = "OPERATOR_REPORT";
	public static final String FAVORITE_REPORT = "FAVORITE_REPORT";
	@Indexed(unique = true)
	private String uuid;
	@Indexed
	private String name;
	private String theme;
	@RelatedTo(type = OPERATOR_ROLE, elementClass = Role.class)
	private Set<Role> roles;
	@RelatedTo(type = OPERATOR_MENU, elementClass = Menu.class)
	private Set<Menu> menus;
	@RelatedTo(type = OPERATOR_REPORT)
	private Set<Report> reports;
	@RelatedTo(type = FAVORITE_REPORT)
	private Set<Report> favoriteReports;

	@NotBlank
	public String getUuid() {
		return uuid;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public String getTheme() {
		if (theme == null)
			theme = Constant.DEFAULT_THEME.getName();
		return theme;
	}

	public Iterable<Role> getRoles() {
		if (roles == null)
			roles = Sets.newHashSet();
		return roles;
	}

	public Iterable<Role> getRoles(Neo4jOperations template) {
		return template.fetch(getRoles());
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

	public Iterable<Report> getFavoriteReports() {
		if (favoriteReports == null)
			favoriteReports = Sets.newHashSet();
		return favoriteReports;
	}

	public Iterable<Report> getFavoriteReports(Neo4jOperations template) {
		return template.fetch(getFavoriteReports());
	}

	public void setFavoriteReports(Set<Report> favoriteReports) {
		this.favoriteReports = favoriteReports;
	}

	public void setRoles(Iterable<Role> roles) {
		this.roles = Sets.newHashSet(roles);
	}

	public void setMenus(Iterable<Menu> menus) {
		this.menus = Sets.newHashSet(menus);
	}

	public void setReports(Iterable<? extends Report> reports) {
		this.reports = Sets.newHashSet(reports);
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public void setTheme(String theme) {
		this.theme = StringUtils.trim(theme);
	}

	public Operator() {
		super();
	}

	public Operator(String uuid, String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}
}
