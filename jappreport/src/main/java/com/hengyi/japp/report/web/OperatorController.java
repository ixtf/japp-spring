package com.hengyi.japp.report.web;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.report.domain.Operator;

@Named
@Scope("view")
public class OperatorController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 3627241517253990782L;
	@Resource(name = "jappCommonSoapClient")
	private com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;
	private Long nodeId;
	private PrincipalType principalType;
	private String principal;
	private Operator operator;

	public void save() {
		try {
			operatorService.save(getOperator());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void newOperator() {
		try {
			UserDTO user = jappCommonSoapClient.findOneUser(principalType,
					principal);
			operator = operatorService.findOne(user);
			redirect(operatorService.getUpdatePath(operator.getNodeId()));
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public Operator getOperator() {
		if (operator == null)
			operator = operatorService.findOne(getNodeId());
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

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipalType(PrincipalType principalType) {
		this.principalType = principalType;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
}
