package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.web.AbstractController;

@Named
@Scope("view")
public class CustomerIndicatorsController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private List<Indicator> indicators;
	private Indicator indicator;

	public List<Indicator> getIndicators() {
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
}
