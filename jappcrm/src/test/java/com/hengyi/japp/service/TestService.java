package com.hengyi.japp.service;

import java.math.BigDecimal;

import org.apache.commons.beanutils.PropertyUtils;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.customer.Customer;

public class TestService {
	public static void main(String[] args) throws Exception {
		Crm crm = new Customer();
		crm.setRegisterCapital(BigDecimal.valueOf(30));
		Object o = PropertyUtils.getProperty(crm, "registerCapital");
		System.out.println((Number) o);
		System.out.println(o instanceof Number);
	}
}
