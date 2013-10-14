package com.hengyi.japp.report.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.CommonConstant;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class Operator extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -4364743082478493931L;
	@Indexed(unique = true)
	private String uuid;
	@Indexed
	private String name;
	private String theme;

	@NotBlank
	public String getUuid() {
		return uuid;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public String getTheme() {
		if (theme == null)
			theme = CommonConstant.DEFAULT_THEME.getName();
		return theme;
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
