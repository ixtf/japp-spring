package com.hengyi.japp.crm.service;

import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerBasicInfoReport;

public interface CustomerService extends CrmService<Customer> {
	CustomerBasicInfoReport basicInfoReport(Long nodeId);
}
