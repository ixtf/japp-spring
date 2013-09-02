package com.hengyi.japp.crm.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.Indexed.Level;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public abstract class Indicator extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 3991202655674862197L;
	/*
	 * 需要通过msg来访问，国际化
	 */
	@NotBlank
	@Indexed(unique = true, level = Level.INSTANCE)
	protected String name;
	@Min(0)
	protected double percent;
	@RelatedToVia(type = IndicatorValueScore.RELATIONSHIP, elementClass = IndicatorValueScore.class)
	@Fetch
	protected Set<IndicatorValueScore> indicatorValueScores;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public double getPercent() {
		return percent;
	}

	public Iterable<IndicatorValueScore> getIndicatorValueScores() {
		if (indicatorValueScores == null)
			indicatorValueScores = Sets.newHashSet();
		return indicatorValueScores;
	}

	public Iterable<IndicatorValueScore> getIndicatorValueScores(
			Neo4jOperations template) {
		return template.fetch(getIndicatorValueScores());
	}

	public List<IndicatorValueScore> getIndicatorValueScoresAsList() {
		return Lists.newArrayList(getIndicatorValueScores());
	}

	public List<IndicatorValueScore> getIndicatorValueScoresAsList(
			Neo4jOperations template) {
		return Lists.newArrayList(getIndicatorValueScores(template));
	}

	public Iterable<IndicatorValue> getIndicatorValues(Neo4jOperations template) {
		Set<IndicatorValue> result = Sets.newHashSet();
		for (IndicatorValueScore indicatorValueScore : getIndicatorValueScores())
			result.add(indicatorValueScore.getEnd(template));
		return result;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public void setIndicatorValueScores(
			Iterable<IndicatorValueScore> indicatorValueScores) {
		this.indicatorValueScores = indicatorValueScores == null ? null : Sets
				.newHashSet(indicatorValueScores);
	}
}
