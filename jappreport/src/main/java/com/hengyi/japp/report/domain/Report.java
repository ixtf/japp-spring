package com.hengyi.japp.report.domain;

import java.io.Serializable;

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
public abstract class Report extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -115312013197219536L;
	@Indexed(level = Level.INSTANCE)
	protected String name;
	@RelatedTo(type = Menu.MENU_REPORT, direction = Direction.INCOMING)
	@Fetch
	protected Menu menu;
	protected String note;

	@NotBlank
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

	@Override
	public String toString() {
		return getName();
	}
}
