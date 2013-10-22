package com.hengyi.japp.report.web;

import java.io.Serializable;

import javax.inject.Named;

import org.ocpsoft.rewrite.annotation.Parameter;
import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.domain.Role;

@Named
@Scope("view")
public class RoleController extends AbstractController implements Serializable {
	private static final long serialVersionUID = 3627241517253990782L;
	@Parameter("id")
	private Long nodeId;
	private Role role;

	public void save() {
		try {
			roleService.save(getRole());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public Role getRole() {
		if (role != null)
			return role;
		if (getNodeId() == null)
			role = new Role();
		else
			role = roleService.findOne(nodeId);
		return role;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
