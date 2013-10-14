package com.hengyi.japp.crm.data;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hengyi.japp.crm.domain.Crm;

@SuppressWarnings("unchecked")
public enum CrmField {
	name("name"), registerNumber("registerNumber"), registerPlace(
			"registerPlace"), registerDate("registerDate"), durationYears(
			"durationYears"), registerCapital("registerCapital"), represent(
			"represent"), addressName("addressName", "address"), zipCode(
			"zipCode"), communicatee("communicatee", "chiefCommunicatee"), communicatees(
			"communicatees", "communicatee"), saleIncome("saleIncome"), associates(
			"associates", "associate"), crmType("crmType"), certificates(
			"certificates", "certificate"), mainBusiness("mainBusiness"), coBusiness(
			"coBusiness"), capacity("capacity");
	private final String fieldName;
	// 通过msg获取，国际化
	private final String displayName;

	private CrmField(String fieldName) {
		this(fieldName, fieldName);
	}

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

	private static Logger log = LoggerFactory.getLogger(CrmField.class);

	public <T> T getValue(Crm crm) {
		try {
			return (T) PropertyUtils.getProperty(crm, fieldName);
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			log.error(crm + "", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return displayName;
	}
}
