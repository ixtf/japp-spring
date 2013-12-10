package com.hengyi.japp.crm.application;

import org.springframework.context.ApplicationEvent;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public abstract class AbstractApplicationEvent extends ApplicationEvent {
	private static final long serialVersionUID = -5317060417145460138L;
	@GraphId
	private Long nodeId;
	private final Long operatorNodeId;

	public AbstractApplicationEvent(final Object source,
			final Long operatorNodeId) {
		super(source);
		this.operatorNodeId = operatorNodeId;
	}

	public final Long getOperatorNodeId() {
		return operatorNodeId;
	}

	public final Long getNodeId() {
		return nodeId;
	}
}
