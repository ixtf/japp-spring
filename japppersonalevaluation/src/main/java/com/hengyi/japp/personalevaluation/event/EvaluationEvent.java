package com.hengyi.japp.personalevaluation.event;

import java.io.Serializable;

import com.hengyi.japp.personalevaluation.domain.node.Person;

public class EvaluationEvent implements Serializable {
	private static final long serialVersionUID = -7011043668344310569L;
	private Long personStartNodeId;
	private Long personEndNodeId;

	public EvaluationEvent(Long personStartNodeId, Long personEndNodeId) {
		super();
		this.personStartNodeId = personStartNodeId;
		this.personEndNodeId = personEndNodeId;
	}

	public EvaluationEvent(Person personStart, Person personEnd) {
		this(personStart.getNodeId(), personEnd.getNodeId());
	}

	public Long getPersonStartNodeId() {
		return personStartNodeId;
	}

	public Long getPersonEndNodeId() {
		return personEndNodeId;
	}

	public void setPersonStartNodeId(Long personStartNodeId) {
		this.personStartNodeId = personStartNodeId;
	}

	public void setPersonEndNodeId(Long personEndNodeId) {
		this.personEndNodeId = personEndNodeId;
	}
}
