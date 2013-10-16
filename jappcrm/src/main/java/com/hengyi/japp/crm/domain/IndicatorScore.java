package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@RelationshipEntity(type = IndicatorScore.RELATIONSHIP)
public class IndicatorScore extends AbstractNeo4j implements
		Serializable {
	private static final long serialVersionUID = 1738240613756847227L;
	public static final String RELATIONSHIP = "INDICATE_SCORE";
	@StartNode
	private Crm start;
	@EndNode
	@Fetch
	private Indicator end;
	private double score;

	public IndicatorScore() {
		super();
	}

	public IndicatorScore(Crm start, Indicator end, double score) {
		this();
		this.start = start;
		this.end = end;
		this.score = score;
	}

	public IndicatorScore(Crm start, Indicator end) {
		this(start, end, 0);
	}

	public Crm getStart() {
		return start;
	}

	public Crm getStart(Neo4jOperations template) {
		return template.fetch(getStart());
	}

	public Indicator getEnd() {
		return end;
	}

	public Indicator getEnd(Neo4jOperations template) {
		return template.fetch(getEnd());
	}

	public void setStart(Crm start) {
		this.start = start;
	}

	public void setEnd(Indicator end) {
		this.end = end;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		IndicatorScore other = (IndicatorScore) o;
		return Objects.equal(getStart(), other.getStart())
				&& Objects.equal(getEnd(), other.getEnd());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getStart(), getEnd());
	}

	@Override
	public String toString() {
		return getStart() + "-" + getEnd() + "-" + getScore();
	}
}
