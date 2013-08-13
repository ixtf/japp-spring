package com.hengyi.japp.crm.domain.node;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public abstract class AbstractIndicator extends AbstractNeo4j implements
		Serializable {
	private static final long serialVersionUID = 4743501451334992586L;
	@NotNull
	protected Double weightPercent;
	@RelatedTo(elementClass = IndicatorValue.class)
	@Fetch
	protected Set<IndicatorValue> indicatorValues;

	public Double getWeightPercent() {
		return weightPercent;
	}

	public void setWeightPercent(Double weightPercent) {
		this.weightPercent = weightPercent;
	}

	public Iterable<IndicatorValue> getIndicatorValues() {
		return indicatorValues;
	}

	public void setIndicatorValues(Iterable<IndicatorValue> indicatorValues) {
		this.indicatorValues = Sets.newHashSet(indicatorValues);
	}
}
