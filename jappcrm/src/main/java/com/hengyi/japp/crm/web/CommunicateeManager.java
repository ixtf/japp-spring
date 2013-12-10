package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.Communicatee;

@Named
@Scope("request")
public class CommunicateeManager extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private Communicatee communicatee;

	public void save() {
		try {
			getCommunicatee().setOperator(getCurrentOperator());
			communicateeService.save(getCommunicatee());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public Communicatee getCommunicatee() {
		if (communicatee == null)
			communicatee = new Communicatee();
		return communicatee;
	}

	public void setCommunicatee(Communicatee communicatee) {
		this.communicatee = communicatee;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
		communicatee = communicateeService.findOne(nodeId);
	}

	public Long getNodeId() {
		return nodeId;
	}
}
