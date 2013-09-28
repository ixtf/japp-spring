package com.hengyi.japp.crm.data;

import org.apache.commons.beanutils.PropertyUtils;

import com.hengyi.japp.crm.domain.Crm;

@SuppressWarnings("unchecked")
public enum CrmField {
	name("name", "name"), registerNumber("registerNumber", "registerNumber"), registerPlace(
			"registerPlace", "registerPlace"), registerDate("registerDate",
			"registerDate"), registerCapital("registerCapital",
			"registerCapital"), represent("represent", "represent"), addressName(
			"addressName", "addressName"), zipCode("zipCode", "zipCode"), communicatee(
			"communicatee", "communicatee"), communicatees("communicatees",
			"communicatee"), saleIncome("saleIncome", "saleIncome"), associates(
			"associates", "associates"), crmType("crmType", "crmType");
	private final String fieldName;
	// 通过msg获取，国际化
	private final String displayName;

	private CrmField(String fieldName, String displayName) {
		this.fieldName = fieldName;
		this.displayName = displayName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public <T> T getValue(Crm crm) throws Exception {
		return (T) PropertyUtils.getProperty(crm, fieldName);
	}

	@Override
	public String toString() {
		return displayName;
	}
}
