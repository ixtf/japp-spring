package com.hengyi.japp.crm.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.data.BugLevel;
import com.hengyi.japp.crm.data.BugStatus;

@NodeEntity
public class Bug extends Modifiable {
	@Indexed
	private String name;
	private String note;
	@NotNull
	private BugLevel level;
	@NotNull
	private BugStatus status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BugLevel getLevel() {
		return level;
	}

	public BugStatus getStatus() {
		return status;
	}

	public void setLevel(BugLevel level) {
		this.level = level;
	}

	public void setStatus(BugStatus status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return getName();
	}
}
