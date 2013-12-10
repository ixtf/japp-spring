package com.hengyi.japp.crm.data;

public enum CrmType {
	CUSTOMER("客户"), STORAGE("仓储");
	private CrmType(String name) {
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
