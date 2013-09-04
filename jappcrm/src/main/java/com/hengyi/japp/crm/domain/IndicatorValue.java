package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class IndicatorValue extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -5080366361699844394L;
	@NotBlank
	@Indexed(unique = true)
	private String name;

	public IndicatorValue() {
		super();
	}

	public IndicatorValue(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getName() == null || getClass() != o.getClass())
			return false;
		IndicatorValue other = (IndicatorValue) o;
		return Objects.equal(getName(), other.getName());
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
