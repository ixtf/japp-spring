package com.hengyi.japp.crm.domain.storage;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.hengyi.japp.crm.data.IndicatorType;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;

@NodeEntity
public class StorageIndicatorCapcital extends StorageIndicator {
	private static final long serialVersionUID = 6158224090934716798L;
	@NotNull
	@RelatedTo
	@Fetch
	private CrmField crmField;

	public StorageIndicatorCapcital() {
		super("仓储容量", 0.08);
		setIndicatorType(IndicatorType.CALCULATE);
		// setCrmField(CrmFieldType.capacity);
	}

	private BigDecimal getValue(Crm crm) {
		return getCrmField().getValue(crm);
	}

	@Override
	public double calculateScore(Crm crm) {
		// 单位：万吨
		double capital = getValue(crm).doubleValue() / 10000;
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

	public CrmField getCrmField() {
		return crmField;
	}

	public void setCrmField(CrmField crmField) {
		this.crmField = crmField;
	}
}
