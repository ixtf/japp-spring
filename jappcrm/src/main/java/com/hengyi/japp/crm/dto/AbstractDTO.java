package com.hengyi.japp.crm.dto;

import java.io.Serializable;

import com.google.common.base.Objects;

public class AbstractDTO implements Serializable {
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
		AbstractDTO other = (AbstractDTO) o;
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
