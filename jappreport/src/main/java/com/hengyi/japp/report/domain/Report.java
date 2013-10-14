package com.hengyi.japp.report.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public abstract class Report extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -115312013197219536L;
	@Indexed
	private String name;
	private String note;

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

	@Override
	public String toString() {
		return getName();
	}
}
