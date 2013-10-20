package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.google.common.base.Objects;

@NodeEntity
public class IndicatorValue extends Modifiable implements Serializable {
	private static final long serialVersionUID = -5080366361699844394L;
	@NotBlank
	@Indexed
	private String name;
	private String note;

	public boolean evaluate(IndicatorValue indicatorValue) {
		return this.equals(indicatorValue);
	}

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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = StringUtils.trim(note);
	}

	// @RelatedToVia(type = IndicatorValueScore.RELATIONSHIP, elementClass =
	// IndicatorValueScore.class, direction = Direction.INCOMING)
	// private Indicator indicator;
	//
	// public Indicator getIndicator() {
	// return indicator;
	// }
	//
	// public void setIndicator(Indicator indicator) {
	// this.indicator = indicator;
	// }

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
