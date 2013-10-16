package com.hengyi.japp.crm.domain.customer;

import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.data.IndicatorType;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;

@NodeEntity
public class CustomerIndicatorDurationYears extends CustomerIndicator {
	private static final long serialVersionUID = 2420361128761811827L;
	@NotNull
	private CrmField crmField;

	public CustomerIndicatorDurationYears() {
		super("已经营年限", 0.06);
		setIndicatorType(IndicatorType.CALCULATE);
		// setCrmField(CrmField.durationYears);
	}

	private int getValue(Crm crm) {
		return getCrmField().getValue(crm);
	}

	@Override
	public double calculateScore(Crm crm) {
		int durationYears = getValue(crm);
		if (durationYears >= 10)
			return 10;
		else if (durationYears >= 5)
			return 9;
		else if (durationYears >= 3)
			return 8;
		else if (durationYears >= 1)
			return 7;
		else
			return 6;
	}

	public CrmField getCrmField() {
		return crmField;
	}

	public void setCrmField(CrmField crmField) {
		this.crmField = crmField;
	}
}
