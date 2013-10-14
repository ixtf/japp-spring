package com.hengyi.japp.crm.domain.storage;

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
public class StorageIndicatorRegisterCapcital extends StorageIndicator {
	private static final long serialVersionUID = 1738599592607754829L;
	@NotNull
	private CrmField crmField;

	public StorageIndicatorRegisterCapcital() {
		super("注册资本", 0.1);
		setIndicatorType(IndicatorType.CALCULATE);
		setCrmField(CrmField.registerCapital);
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
		double registerCapital = getValue(crm).doubleValue() / 100000000;
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

	public CrmField getCrmField() {
		return crmField;
	}

	public void setCrmField(CrmField crmField) {
		this.crmField = crmField;
	}
}
