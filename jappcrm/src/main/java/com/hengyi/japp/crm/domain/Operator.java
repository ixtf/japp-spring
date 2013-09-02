package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.hengyi.japp.common.Constant;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class Operator extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -4364743082478493931L;
	public static final String NAME_SEARCH = "operatorNameSearch";
	@NotBlank
	@Indexed(unique = true)
	private String uuid;
	@NotBlank
	@Indexed(indexType = IndexType.FULLTEXT, indexName = NAME_SEARCH)
	private String name;
	private String theme;

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTheme() {
		if (theme == null)
			theme = Constant.DEFAULT_THEME.getName();
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
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
