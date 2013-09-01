package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.URL;
import com.hengyi.japp.crm.domain.Communicatee;

@Named
@Scope("request")
public class CommunicateeController extends AbstractController implements Serializable{
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private Communicatee communicatee;

	public void save() {
		try {
			communicateeService.save(communicatee);
			redirect(URL.COMMUNICATEES);
		} catch (Exception e) {
			addErrorMessage(e);
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
