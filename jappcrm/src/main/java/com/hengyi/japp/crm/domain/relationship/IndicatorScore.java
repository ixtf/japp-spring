package com.hengyi.japp.crm.domain.relationship;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.crm.domain.node.AbstractCrm;
import com.hengyi.japp.crm.domain.node.AbstractIndicator;

@RelationshipEntity
public class IndicatorScore extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -3291124751285101268L;
	public static final String RELATIONSHIP = "INDICATOR_SCORE";
	@StartNode
	private AbstractCrm start;
	@EndNode
	private AbstractIndicator end;
	@NotNull
	private Double score;

	public AbstractCrm getStart() {
		return start;
	}

	public AbstractIndicator getEnd() {
		return end;
	}

	public Double getScore() {
		return score;
	}

	public void setStart(AbstractCrm start) {
		this.start = start;
	}

	public void setEnd(AbstractIndicator end) {
		this.end = end;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}
