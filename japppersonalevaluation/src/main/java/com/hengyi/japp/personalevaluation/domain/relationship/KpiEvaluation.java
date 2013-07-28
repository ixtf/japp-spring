package com.hengyi.japp.personalevaluation.domain.relationship;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.personalevaluation.domain.node.Kpi;
import com.hengyi.japp.personalevaluation.domain.node.Person;

@RelationshipEntity
public class KpiEvaluation extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -7199139697143099203L;
	public static final String RELATIONSHIP = "KPI_EVALUATION";
	@StartNode
	private Person person;
	@EndNode
	private Kpi kpi;

	@Min(0)
	private Double score;
	private Date date = new Date();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getScore() {
		if (score == null)
			score = new Double(0);
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Kpi getKpi() {
		return kpi;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setKpi(Kpi kpi) {
		this.kpi = kpi;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Person getPerson(Neo4jOperations template) {
		return template.fetch(getPerson());
	}
}
