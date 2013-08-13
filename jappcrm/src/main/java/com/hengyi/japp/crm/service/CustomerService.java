package com.hengyi.japp.crm.service;

import com.hengyi.japp.crm.domain.node.customer.Customer;

public interface CustomerService {
	Customer findOne(String uuid);

	void save(Customer customer) throws Exception;
}
