package com.hengyi.japp.report.domain;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class Role extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 1896078677101981112L;
	@Indexed(unique = true)
	private String name;
	private String note;
	@RelatedTo
	private Set<Operator> operators;

	@NotBlank
	public String getName() {
		return name;
	}

	public String getNote() {
		return note;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public void setNote(String note) {
		this.note = StringUtils.trim(note);
	}

	public Iterable<Operator> getOperators() {
		if (operators == null)
			operators = Sets.newHashSet();
		return operators;
	}

	public Iterable<Operator> getOperators(Neo4jOperations template) {
		return template.fetch(getOperators());
	}

	public void setOperators(Iterable<Operator> operators) {
		this.operators = Sets.newHashSet(operators);
	}

	@Override
	public String toString() {
		return getName();
	}
}
