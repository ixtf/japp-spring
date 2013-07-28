package com.hengyi.japp.personalevaluation.event;

import java.io.Serializable;

public class EvaluationEvent implements Serializable {
	private static final long serialVersionUID = -7011043668344310569L;
	private Long personStartNodeId;
	private Long personEndNodeId;

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
