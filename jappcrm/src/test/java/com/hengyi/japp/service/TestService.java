package com.hengyi.japp.service;

import org.apache.commons.beanutils.PropertyUtils;

import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.customer.Customer;

public class TestService {
	public static void main(String[] args) throws Exception {
		Crm crm = new Customer();
		System.out.println((Communicatee) PropertyUtils.getProperty(crm,
				"communicatee"));
	}
}
