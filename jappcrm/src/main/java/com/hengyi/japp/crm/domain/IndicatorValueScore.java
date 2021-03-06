package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@RelationshipEntity(type = IndicatorValueScore.RELATIONSHIP)
public class IndicatorValueScore extends AbstractNeo4j
		implements Serializable, Comparable<IndicatorValueScore> {
	private static final long serialVersionUID = -5080366361699844394L;
	public static final String RELATIONSHIP = "INDICATORVALUE_SCORE";
	@StartNode
	private Indicator start;
	@EndNode
	@Fetch
	private IndicatorValue end;
	@Min(0)
	private double score;

	public IndicatorValueScore() {
		super();
	}

	public IndicatorValueScore(Indicator start, IndicatorValue end,
			double score) {
		super();
		this.start = start;
		this.end = end;
		this.score = score;
	}

	public IndicatorValueScore(Indicator start, IndicatorValue end) {
		this(start, end, 0);
	}

	public IndicatorValueScore(Indicator start) {
		this(start, null);
	}

	public Indicator getStart() {
		return start;
	}

	public Indicator getStart(Neo4jOperations template) {
		return template.fetch(getStart());
	}

	public IndicatorValue getEnd() {
		return end;
	}

	public IndicatorValue getEnd(Neo4jOperations template) {
		return template.fetch(getEnd());
	}

	public double getScore() {
		return score;
	}

	public void setStart(Indicator start) {
		this.start = start;
	}

	public void setEnd(IndicatorValue end) {
		this.end = end;
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
		IndicatorValueScore other = (IndicatorValueScore) o;
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

	@Override
	public int compareTo(IndicatorValueScore o) {
		return o == null ? -1 : Double.compare(getScore(), o.getScore());
	}
}
