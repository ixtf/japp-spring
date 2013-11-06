package com.hengyi.japp.report.event.role;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.report.domain.Role;

public class RoleUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = 8188056894052475891L;

	public RoleUpdateEvent(Long nodeId) {
		super(nodeId);
	}

	public RoleUpdateEvent(Role role) {
		this(role.getNodeId());
	}

	public Long getNodeId() {
		return (Long) super.getSource();
	}
}
