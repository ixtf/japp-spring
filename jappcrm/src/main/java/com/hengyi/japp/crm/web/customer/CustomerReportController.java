package com.hengyi.japp.crm.web.customer;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerBasicInfoReport;
import com.hengyi.japp.crm.domain.customer.CustomerCreditRiskReport;
import com.hengyi.japp.crm.domain.customer.CustomerFiCreditRiskReport;
import com.hengyi.japp.crm.web.AbstractController;

@Named
@Scope("request")
public class CustomerReportController extends AbstractController {
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private Customer customer;

	public CustomerBasicInfoReport getBasicInfoReport() {
		return customer;
	}

	public CustomerCreditRiskReport getCreditRiskReport() {
		return customer;
	}

	public CustomerFiCreditRiskReport getFiCreditRiskReport() {
		return customer;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
		customer = customerService.findOne(nodeId);
	}
}
