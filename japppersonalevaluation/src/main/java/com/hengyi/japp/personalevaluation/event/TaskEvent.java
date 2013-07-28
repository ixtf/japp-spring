package com.hengyi.japp.personalevaluation.event;

import java.io.Serializable;

public class TaskEvent implements Serializable {
	private static final long serialVersionUID = -5209951917448756084L;
	private Long taskNodeId;
	private Type type;

	enum Type {
		ACTIVE, FINISH
	}

	public Long getTaskNodeId() {
		return taskNodeId;
	}

	public void setTaskNodeId(Long taskNodeId) {
		this.taskNodeId = taskNodeId;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
