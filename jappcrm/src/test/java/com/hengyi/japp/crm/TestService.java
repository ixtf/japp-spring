package com.hengyi.japp.crm;

import java.math.BigDecimal;
import java.text.MessageFormat;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.customer.Customer;

public class TestService {
	public static void main(String[] args) throws Exception {
		String s = "tom.tom";
		String[] a = s.split("\\.");
		System.out.println(a);

		DefaultPasswordService ps = new DefaultPasswordService();
		DefaultHashService hs = new DefaultHashService();
		hs.setHashAlgorithmName("SHA-512");
		hs.setHashIterations(5000);
		ps.setHashService(hs);
		System.out.println(ps.encryptPassword("57442577"));

		MessageFormat f = new MessageFormat("CRM[{0}]");
		System.out.print(String.format("CRM[%d]", new Long(10)));
		System.out.print(f.format(new Long(10).toString()));
		Crm crm = new Customer();
		crm.setRegisterCapital(BigDecimal.valueOf(30));
		Object o = PropertyUtils.getProperty(crm, "registerCapital");
		System.out.println((Number) o);
		System.out.println(o instanceof Number);
	}
}
