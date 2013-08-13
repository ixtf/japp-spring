package com.hengyi.japp.crm.domain.relationship;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.crm.domain.node.AbstractCrm;

@RelationshipEntity
public class Associate extends AbstractNeo4j {
	public static final String RELATIONSHIP = "ASSOCIATE";
	@StartNode
	private AbstractCrm start;
	@EndNode
	private AbstractCrm end;
	private String note;

	public AbstractCrm getStart() {
		return start;
	}

	public AbstractCrm getEnd() {
		return end;
	}

	public String getNote() {
		return note;
	}

	public void setStart(AbstractCrm start) {
		this.start = start;
	}

	public void setEnd(AbstractCrm end) {
		this.end = end;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
