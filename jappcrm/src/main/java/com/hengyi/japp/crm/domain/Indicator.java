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

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.crm.data.IndicatorType;

@NodeEntity
public abstract class Indicator extends Modifiable implements Serializable {
	private static final long serialVersionUID = 3991202655674862197L;
	@NotBlank
	@Indexed(unique = true, level = Level.INSTANCE)
	protected String name;
	@Min(0)
	protected double percent;
	@Min(0)
	protected IndicatorType indicatorType = IndicatorType.SIMPLE;
	@RelatedToVia(type = IndicatorValueScore.RELATIONSHIP, elementClass = IndicatorValueScore.class)
	@Fetch
	protected Set<IndicatorValueScore> indicatorValueScores;

	public final double calculateScorePercent(Crm crm) {
		return calculateScore(crm) * getPercent();
	}

	public double calculateScore(Crm crm) {
		double result = 0;
		Set<IndicatorValue> indicatorValues = ImmutableSet.copyOf(crm
				.getIndicatorValues());
		for (IndicatorValueScore indicatorValueScore : getIndicatorValueScores()) {
			if (!indicatorValues.contains(indicatorValueScore.getEnd()))
				continue;
			double score = indicatorValueScore.getScore();
			result = result < score ? score : result;
		}
		return result;
	}

	public List<IndicatorValueScore> getIndicatorValueScoresAsList() {
		return Lists.newArrayList(getIndicatorValueScores());
	}

	public List<IndicatorValue> getIndicatorValues(Crm crm,
			Neo4jOperations template) {
		return template.fetch(getIndicatorValues(crm));
	}

	public List<IndicatorValue> getIndicatorValues(Crm crm) {
		List<IndicatorValue> result = Lists.newArrayList();
		Set<IndicatorValue> indicatorValues = ImmutableSet.copyOf(crm
				.getIndicatorValues());
		for (IndicatorValueScore indicatorValueScore : getIndicatorValueScores()) {
			IndicatorValue indicatorValue = indicatorValueScore.getEnd();
			if (indicatorValues.contains(indicatorValue))
				result.add(indicatorValue);
		}
		return result;
	}

	public Indicator() {
		super();
	}

	public Indicator(String name, double percent) {
		super();
		this.name = name;
		this.percent = percent;
	}

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
		if (IndicatorType.CALCULATE.equals(indicatorType)
				|| indicatorValueScores == null)
			indicatorValueScores = Sets.newTreeSet();
		return indicatorValueScores;
	}

	public Iterable<IndicatorValueScore> getIndicatorValueScores(
			Neo4jOperations template) {
		return template.fetch(getIndicatorValueScores());
	}

	public List<IndicatorValueScore> getIndicatorValueScoresAsList(
			Neo4jOperations template) {
		return Lists.newArrayList(getIndicatorValueScores(template));
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public void setIndicatorValueScores(
			Iterable<IndicatorValueScore> indicatorValueScores) {
		this.indicatorValueScores = indicatorValueScores == null ? null : Sets
				.newHashSet(indicatorValueScores);
	}

	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public void setIndicatorType(IndicatorType indicatorType) {
		this.indicatorType = indicatorType;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
