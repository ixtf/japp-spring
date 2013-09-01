package com.hengyi.japp.crm.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@RelationshipEntity(type = Associate.RELATIONSHIP)
public class Associate extends AbstractNeo4j {
	public static final String RELATIONSHIP = "ASSOCIATE";
	@StartNode
	private Crm start;
	@EndNode
	private Crm end;
	private String note;

	public Crm getStart() {
		return start;
	}

	public Crm getStart(Neo4jOperations template) {
		return template.fetch(getStart());
	}

	public Crm getEnd() {
		return end;
	}

	public Crm getEnd(Neo4jOperations template) {
		return template.fetch(getEnd());
	}

	public String getNote() {
		return note;
	}

	public void setStart(Crm start) {
		this.start = start;
	}

	public void setEnd(Crm end) {
		this.end = end;
	}

	public void setNote(String note) {
		this.note = StringUtils.trim(note);
	}
}
