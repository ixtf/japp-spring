package com.hengyi.japp.report.domain;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class Menu extends AbstractNeo4j implements Serializable,
		Comparable<Menu> {
	private static final long serialVersionUID = 2553875982016927076L;
	public static final String SUBMENU = "SUBMENU";
	public static final String MENU_REPORT = "MENU_REPORT";
	@NotBlank
	@Indexed
	private String name;
	@RelatedTo(type = SUBMENU, direction = Direction.INCOMING)
	@Fetch
	private Menu parent;
	@RelatedTo(type = SUBMENU, elementClass = Menu.class)
	private Set<Menu> subs;
	private double sortBy;

	public String getName() {
		return name;
	}

	public Menu getParent() {
		return parent;
	}

	public Iterable<Menu> getSubs() {
		if (subs == null)
			subs = Sets.newHashSet();
		return subs;
	}

	public Iterable<Menu> getSubs(Neo4jOperations template) {
		return template.fetch(getSubs());
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public void setSubs(Iterable<Menu> subs) {
		this.subs = Sets.newHashSet(subs);
	}

	public double getSortBy() {
		return sortBy;
	}

	public void setSortBy(double sortBy) {
		this.sortBy = sortBy;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int compareTo(Menu o) {
		return Double.compare(getSortBy(), o.getSortBy());
	}
}
