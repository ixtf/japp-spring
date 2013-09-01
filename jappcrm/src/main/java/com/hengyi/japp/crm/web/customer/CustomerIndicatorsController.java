package com.hengyi.japp.crm.web.customer;

import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.URL;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.web.AbstractController;

@Named
@Scope("view")
public class CustomerIndicatorsController extends AbstractController {
	private static final long serialVersionUID = -6359781138513690580L;
	private List<CustomerIndicator> indicators;
	private CustomerIndicator indicator;

	public void edit() {
		redirect(URL.CUSTOMERS + "/" + indicator.getNodeId());
	}

	public List<CustomerIndicator> getIndicators() {
		if (indicators == null)
			indicators = customerService.findAllIndicator();
		return indicators;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(CustomerIndicator indicator) {
		this.indicator = indicator;
	}

	public void setIndicators(List<CustomerIndicator> indicators) {
		this.indicators = indicators;
	}
}