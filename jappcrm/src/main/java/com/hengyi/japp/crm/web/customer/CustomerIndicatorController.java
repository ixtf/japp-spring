package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.web.IndicatorController;

@Named
@Scope("view")
public class CustomerIndicatorController extends IndicatorController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;

	@Override
	protected Indicator newIndicator() {
		return new CustomerIndicator();
	}
}
