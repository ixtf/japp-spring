package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class IndicatorValue extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -5080366361699844394L;
	@NotBlank
	@Indexed
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
