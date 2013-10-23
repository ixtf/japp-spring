package com.hengyi.japp.crm.domain.customer;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;

@NodeEntity
public class CustomerIndicator extends Indicator {
    private static final long serialVersionUID = 2880562946828303484L;

    @Override
    public double calculateScore(Crm crm) {
	if (crmField == null)
	    return super.calculateScore(crm);
	if ("durationYears".equals(crmField.getFieldName()))
	    return durationYears(crm);
	else if ("registerCapcital".equals(crmField.getFieldName()))
	    return registerCapcital(crm);
	else if ("saleIncome".equals(crmField.getFieldName()))
	    return saleIncome(crm);
	return 0;
    }

    private double durationYears(Crm crm) {
	int durationYears = crmField.getValue(crm);
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

    private double registerCapcital(Crm crm) {
	// 单位：亿
	double registerCapital = ((Number) crmField.getValue(crm))
		.doubleValue() / 100000000;
	if (registerCapital >= 5)
	    return 10;
	else if (registerCapital >= 3)
	    return 9;
	else if (registerCapital >= 2)
	    return 8;
	else if (registerCapital >= 1)
	    return 7;
	else if (registerCapital >= 0.5)
	    return 6;
	else if (registerCapital >= 0.3)
	    return 5;
	else if (registerCapital >= 0.2)
	    return 4;
	else if (registerCapital >= 0.1)
	    return 3;
	else if (registerCapital >= 0.05)
	    return 2;
	else
	    return 1;
    }

    private double saleIncome(Crm crm) {
	// 单位：亿
	double saleIncome = ((Number) crmField.getValue(crm)).doubleValue() / 100000000;
	if (saleIncome >= 1000)
	    return 10;
	else if (saleIncome >= 500)
	    return 9;
	else if (saleIncome >= 300)
	    return 8;
	else if (saleIncome >= 100)
	    return 7;
	else if (saleIncome >= 50)
	    return 6;
	else if (saleIncome >= 10)
	    return 5;
	else if (saleIncome >= 1)
	    return 4;
	else if (saleIncome >= 0.5)
	    return 3;
	else if (saleIncome >= 0.1)
	    return 2;
	else
	    return 1;
    }

    public CustomerIndicator() {
	super();
    }

    public CustomerIndicator(String name, double percent, CrmField crmField) {
	super();
	this.name = name;
	this.percent = percent;
	this.crmField = crmField;
    }
}
