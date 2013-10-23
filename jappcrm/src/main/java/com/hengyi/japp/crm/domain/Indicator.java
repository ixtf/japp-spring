package com.hengyi.japp.crm.domain;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.Indexed.Level;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@NodeEntity
public abstract class Indicator extends Modifiable {
    private static final long serialVersionUID = 6081489024094336426L;
    public static final String INDICATOR_CRMFIELD = "INDICATOR_CRMFIELD";
    @NotBlank
    @Indexed(unique = true, level = Level.INSTANCE)
    protected String name;
    @Min(0)
    protected double percent;
    @RelatedTo(type = INDICATOR_CRMFIELD)
    @Fetch
    protected CrmField crmField;
    @RelatedToVia(type = IndicatorValueScore.RELATIONSHIP, elementClass = IndicatorValueScore.class)
    @Fetch
    protected Set<IndicatorValueScore> indicatorValueScores;

    public String getName() {
	return name;
    }

    public double getPercent() {
	return percent;
    }

    public List<IndicatorValueScore> getIndicatorValueScores() {
	if (indicatorValueScores == null)
	    indicatorValueScores = Sets.newHashSet();
	return Lists.newArrayList(indicatorValueScores);
    }

    public List<IndicatorValue> getIndicatorValues() {
	List<IndicatorValue> result = Lists.newArrayList();
	for (IndicatorValueScore indicatorValueScore : getIndicatorValueScores())
	    result.add(indicatorValueScore.getEnd());
	return result;
    }

    public final double calculateScorePercent(Crm crm) {
	return calculateScore(crm) * getPercent();
    }

    public double calculateScore(Crm crm) {
	double result = 0;
	Set<IndicatorValue> indicatorValues = Sets.newHashSet(crm
		.getIndicatorValues());
	for (IndicatorValueScore indicatorValueScore : getIndicatorValueScores()) {
	    if (!indicatorValues.contains(indicatorValueScore.getEnd()))
		continue;
	    double score = indicatorValueScore.getScore();
	    result = result < score ? score : result;
	}
	return result;
    }

    public void setName(String name) {
	this.name = StringUtils.trim(name);
    }

    public void setPercent(double percent) {
	this.percent = percent;
    }

    public void setIndicatorValueScores(
	    Iterable<IndicatorValueScore> indicatorValueScores) {
	this.indicatorValueScores = indicatorValueScores == null ? null : Sets
		.newHashSet(indicatorValueScores);
    }

    public CrmField getCrmField() {
	return crmField;
    }

    public void setCrmField(CrmField crmField) {
	this.crmField = crmField;
    }

    @Override
    public String toString() {
	return this.getName();
    }

    // public List<IndicatorValue> getIndicatorValues(Crm crm) {
    // if (IndicatorType.CALCULATE.equals(this.getIndicatorType()))
    // return Lists.newArrayList(new IndicatorValue(String
    // .valueOf(getValue(crm))));
    // return ImmutableList.of();
    // List<IndicatorValue> result = Lists.newArrayList();
    // Set<IndicatorValue> indicatorValues = ImmutableSet.copyOf(crm
    // .getIndicatorValues());
    // for (IndicatorValueScore indicatorValueScore : getIndicatorValueScores())
    // {
    // IndicatorValue indicatorValue = indicatorValueScore.getEnd();
    // if (indicatorValues.contains(indicatorValue))
    // result.add(indicatorValue);
    // }
    // return result;
    // }
}
