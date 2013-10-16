package com.hengyi.japp.common.domain.shared;

import java.io.Serializable;

import org.springframework.data.neo4j.annotation.GraphId;

import com.google.common.base.Objects;

public abstract class AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 5367146318436443822L;
	@GraphId
	protected Long nodeId;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getNodeId() == null || getClass() != o.getClass())
			return false;
		AbstractNeo4j other = (AbstractNeo4j) o;
		return Objects.equal(getNodeId(), other.getNodeId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getNodeId());
	}

	@Override
	public String toString() {
		if (getNodeId() == null)
			return null;
		return String.valueOf(getNodeId());
	}
}
