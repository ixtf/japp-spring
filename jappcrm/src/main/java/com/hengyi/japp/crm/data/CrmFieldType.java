package com.hengyi.japp.crm.data;

public enum CrmFieldType {
	CRM("公共"), CUSTOMER("客户"), STORAGE("仓储");
	private CrmFieldType(String name) {
		this.name = name;
	}

	private final String name;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	// name("name"), registerNumber("registerNumber"), registerPlace(
	// "registerPlace"), registerDate("registerDate"), registerInvalidDate(
	// "registerInvalidDate"), durationYears("durationYears"), registerCapital(
	// "registerCapital"), represent("represent"), addressName(
	// "addressName", "address"), zipCode("zipCode"), communicatee(
	// "communicatee", "chiefCommunicatee"), communicatees(
	// "communicatees", "communicatee"), saleIncome("saleIncome"), associates(
	// "associates", "associate"), crmTypes("crmTypes"), certificates(
	// "certificates", "certificate"), mainBusiness("mainBusiness"), coBusiness(
	// "coBusiness"), capacity("capacity");
	// private final String fieldName;
	// // 通过msg获取，国际化
	// private final String displayName;
	//
	// private CrmFieldType(String fieldName) {
	// this(fieldName, fieldName);
	// }
	//
	// private CrmFieldType(String fieldName, String displayName) {
	// this.fieldName = fieldName;
	// this.displayName = displayName;
	// }
	//
	// public String getFieldName() {
	// return fieldName;
	// }
	//
	// public String getDisplayName() {
	// return displayName;
	// }
	//
	// private static Logger log = LoggerFactory.getLogger(CrmFieldType.class);
	//
	// public <T> T getValue(Crm crm) {
	// try {
	// return (T) PropertyUtils.getProperty(crm, fieldName);
	// } catch (IllegalAccessException | InvocationTargetException
	// | NoSuchMethodException e) {
	// log.error(crm + "-" + this, e);
	// // throw new RuntimeException(e);
	// return null;
	// }
	// }
	//
	// @Override
	// public String toString() {
	// return displayName;
	// }
}
