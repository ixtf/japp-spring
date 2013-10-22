package com.hengyi.japp.crm.domain.storage;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.data.IndicatorType;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.IndicatorValue;

@NodeEntity
public class StorageIndicatorDurationYears extends StorageIndicator {
	private static final long serialVersionUID = 5437801085834499213L;
	@NotNull
	private CrmField crmField;

	public StorageIndicatorDurationYears() {
		super("已经营年限", 0.04);
		setIndicatorType(IndicatorType.CALCULATE);
		// setCrmField(CrmField.durationYears);
	}

	@Override
	public List<IndicatorValue> getIndicatorValues(Crm crm) {
		int i = crmField.getValue(crm);
		return Lists.newArrayList(new IndicatorValue(String.valueOf(i)));
	}

	@Override
	public double calculateScore(Crm crm) {
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

	public CrmField getCrmField() {
		return crmField;
	}

	public void setCrmField(CrmField crmField) {
		this.crmField = crmField;
	}
}
