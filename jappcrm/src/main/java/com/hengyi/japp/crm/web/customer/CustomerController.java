package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;
import java.util.Set;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Sets;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.web.CrmController;

@Named
@Scope("view")
public class CustomerController extends CrmController implements Serializable {
	private static final long serialVersionUID = 3708518912737819900L;

	@Override
	protected Crm newCrm() {
		return new Customer();
	}

	@Override
	protected Iterable<Indicator> getAssociatedIndicators() {
		Set<Indicator> result = Sets.newHashSet();
		for (Indicator indicator : customerService.findAllIndicator())
			result.add(indicator);
		return result;
	}
}
