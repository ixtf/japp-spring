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
}
