package com.hengyi.japp.crm.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@RelationshipEntity(type = Associate.RELATIONSHIP)
public class Associate extends AbstractNeo4j {
	public static final String RELATIONSHIP = "ASSOCIATE";
	@StartNode
	@Fetch
	private Crm start;
	@EndNode
	@Fetch
	private Crm end;
	private String note;

	public Associate() {
		super();
	}

	public Associate(Crm start) {
		super();
		this.start = start;
	}

	public Crm getAssociate(Crm crm) {
		if (crm == null)
			return null;
		if (crm.equals(getStart()))
			return getEnd();
		else if (crm.equals(getEnd()))
			return getStart();
		else
			return null;
	}

	public Crm getAssociate(Crm crm, Neo4jOperations template) {
		return template.fetch(getAssociate(crm));
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Associate other = (Associate) o;
		return (Objects.equal(getStart(), other.getStart()) && Objects.equal(
				getEnd(), other.getEnd()))
				|| (Objects.equal(getStart(), other.getEnd()) && Objects.equal(
						getEnd(), other.getStart()));
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getStart(), getEnd());
	}

	@Override
	public String toString() {
		return getStart() + "-" + getEnd();
	}
}
