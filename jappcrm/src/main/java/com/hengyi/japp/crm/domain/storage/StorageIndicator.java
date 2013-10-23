package com.hengyi.japp.crm.domain.storage;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;

@NodeEntity
public class StorageIndicator extends Indicator {
    private static final long serialVersionUID = 8640666656378277929L;

    @Override
    public double calculateScore(Crm crm) {
	if (crmField == null)
	    return super.calculateScore(crm);
	if ("durationYears".equals(crmField.getFieldName()))
	    return durationYears(crm);
	else if ("registerCapcital".equals(crmField.getFieldName()))
	    return registerCapcital(crm);
	else if ("saleIncome".equals(crmField.getFieldName()))
	    return capcital(crm);
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

    private double capcital(Crm crm) {
	// 单位：万吨
	double capital = ((Number) crmField.getValue(crm)).doubleValue() / 10000;
	if (capital >= 100)
	    return 10;
	else if (capital >= 70)
	    return 9;
	else if (capital >= 50)
	    return 8;
	else if (capital >= 30)
	    return 7;
	else if (capital >= 10)
	    return 6;
	else if (capital >= 5)
	    return 5;
	else
	    return 4;
    }

    public StorageIndicator() {
	super();
    }

    public StorageIndicator(String name, double percent, CrmField crmField) {
	super();
	this.name = name;
	this.percent = percent;
	this.crmField = crmField;
    }
}
