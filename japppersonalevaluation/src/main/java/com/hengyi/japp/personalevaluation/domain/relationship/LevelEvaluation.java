package com.hengyi.japp.personalevaluation.domain.relationship;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.personalevaluation.domain.data.Level;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.sun.istack.NotNull;

@RelationshipEntity
public class LevelEvaluation extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 1781459693769584314L;
	public static final String RELATIONSHIP = "LEVEL_EVALUATION";
	@StartNode
	private Person personStart;
	@EndNode
	private Person personEnd;
	@Indexed(indexType = IndexType.SIMPLE)
	private Level level;
	@NotBlank
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "personSummarySearch")
	private String summary;
	@NotNull
	private Date date;

	public LevelEvaluation() {
		super();
	}

	public LevelEvaluation(Person personStart, Person personEnd) {
		super();
		this.personStart = personStart;
		this.personEnd = personEnd;
		level = Level.L5;
		date = new Date();
	}

	public Level getLevel() {
		if (level == null)
			level = Level.L5;
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Date getDate() {
		if (date == null)
			date = new Date();
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSummary() {
		if (summary == null)
			summary = "";
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Person getPersonStart() {
		return personStart;
	}

	public Person getPersonEnd() {
		return personEnd;
	}

	public void setPersonStart(Person personStart) {
		this.personStart = personStart;
	}

	public void setPersonEnd(Person personEnd) {
		this.personEnd = personEnd;
	}

	public Person getPersonStart(Neo4jOperations template) {
		return template.fetch(getPersonStart());
	}

	public Person getPersonEnd(Neo4jOperations template) {
		return template.fetch(getPersonEnd());
	}
}
