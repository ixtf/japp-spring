package com.hengyi.japp.report.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.report.domain.Operator;

@Named
@Scope("view")
public class OperatorController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 3627241517253990782L;
	private Long nodeId;
	private PrincipalType principalType;
	private Object principal;
	private Operator operator;

	public void save() {
		try {
			operatorService.save(getOperator());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public Operator getOperator() {
		if (operator != null)
			return operator;
		if (nodeId == null)
			operator = new Operator();
		else
			operator = operatorService.findOne(nodeId);
		return operator;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public PrincipalType getPrincipalType() {
		return principalType;
	}

	public Object getPrincipal() {
		return principal;
	}

	public void setPrincipalType(PrincipalType principalType) {
		this.principalType = principalType;
	}

	public void setPrincipal(Object principal) {
		this.principal = principal;
	}
}
