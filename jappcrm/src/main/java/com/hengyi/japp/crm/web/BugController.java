package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.Bug;
import com.hengyi.japp.crm.service.BugService;

@Named
@Scope("request")
public class BugController extends AbstractController implements Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	@Inject
	private BugService bugService;
	private Long nodeId;
	private Bug bug;

	public void save() {
		try {
			bugService.save(bug);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public Bug getBug() {
		if (bug != null)
			return bug;
		if (nodeId == null)
			bug = new Bug();
		else
			bug = bugService.findOne(nodeId);
		return bug;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}
}
