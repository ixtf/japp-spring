package com.hengyi.japp.personalevaluation.domain.node;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.personalevaluation.domain.relationship.KpiEvaluation;

@NodeEntity
public class Kpi extends AbstractNeo4j {
	public static final String KPI_PERSON = "KPI_PERSON";
	private String name;
	private Double minScore;
	private Double maxScore;
	@RelatedTo(type = KPI_PERSON)
	private Person person;
	@RelatedToVia(type = KpiEvaluation.RELATIONSHIP, direction = Direction.INCOMING, elementClass = KpiEvaluation.class)
	private Set<KpiEvaluation> kpiEvaluations;

	public Iterable<KpiEvaluation> getKpiEvaluations() {
		if (kpiEvaluations == null)
			kpiEvaluations = Sets.newHashSet();
		return kpiEvaluations;
	}

	public Iterable<KpiEvaluation> getKpiEvaluations(Neo4jOperations template) {
		return template.fetch(getKpiEvaluations());
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMinScore() {
		return minScore;
	}

	public void setMinScore(Double minScore) {
		this.minScore = minScore;
	}

	public void setMinScore(Integer minScore) {
		this.minScore = (double) minScore;
	}

	public Double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = (double) maxScore;
	}
}
