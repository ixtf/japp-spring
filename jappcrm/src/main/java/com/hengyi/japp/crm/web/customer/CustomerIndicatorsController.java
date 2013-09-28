package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.service.IndicatorService;
import com.hengyi.japp.crm.service.customer.CustomerIndicatorService;
import com.hengyi.japp.crm.web.IndicatorsController;

@Named
@Scope("view")
public class CustomerIndicatorsController extends
		IndicatorsController<CustomerIndicator> implements Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	@Inject
	private CustomerIndicatorService customerIndicatorService;

	@Override
	protected IndicatorService<CustomerIndicator> getIndicatorService() {
		return customerIndicatorService;
	}
}
