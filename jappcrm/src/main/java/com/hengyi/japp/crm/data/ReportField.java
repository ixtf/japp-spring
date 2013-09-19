package com.hengyi.japp.crm.data;

import org.apache.commons.beanutils.PropertyUtils;

@SuppressWarnings("unchecked")
public enum ReportField {
	name("name", "name"), registerNumber("registerNumber", "registerNumber"), registerPlace(
			"registerPlace", "registerPlace"), registerDate("registerDate",
			"registerDate"), registerCapital("registerCapital",
			"registerCapital"), represent("represent", "represent"), addressName(
			"addressName", "addressName"), zipCode("zipCode", "zipCode"), communicatee(
			"communicatee", "communicatee"), communicatees("communicatees",
			"communicatee"), saleIncome("saleIncome", "saleIncome"), associates(
			"associates", "associates"), crmType("crmType", "crmType");
	private final String _fieldName;
	// 通过msg获取，国际化
	private final String _displayName;

	private ReportField(String _fieldName, String _displayName) {
		this._fieldName = _fieldName;
		this._displayName = _displayName;
	}

	public <T> T getValue(Object o) throws Exception {
		return (T) PropertyUtils.getProperty(o, _fieldName);
	}

	@Override
	public String toString() {
		return _displayName;
	}
}
