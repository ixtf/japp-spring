package com.hengyi.japp.crm.domain.customer;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.data.CrmField;
import com.hengyi.japp.crm.data.IndicatorType;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.IndicatorValue;

@NodeEntity
public class CustomerIndicatorSaleIncome extends CustomerIndicator {
	private static final long serialVersionUID = -6811556126201545896L;
	@NotNull
	private CrmField crmField;

	public CustomerIndicatorSaleIncome() {
		super("销售收入", 0.12);
		setIndicatorType(IndicatorType.CALCULATE);
		setCrmField(CrmField.saleIncome);
	}

	private BigDecimal getValue(Crm crm) {
		return getCrmField().getValue(crm);
	}

	@Override
	public List<IndicatorValue> getIndicatorValues(Crm crm) {
		return Lists.newArrayList(new IndicatorValue(String
				.valueOf(getValue(crm))));
	}

	@Override
	public double calculateScore(Crm crm) {
		// 单位：亿
		double saleIncome = getValue(crm).doubleValue() / 100000000;
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

	public CrmField getCrmField() {
		return crmField;
	}

	public void setCrmField(CrmField crmField) {
		this.crmField = crmField;
	}
}
