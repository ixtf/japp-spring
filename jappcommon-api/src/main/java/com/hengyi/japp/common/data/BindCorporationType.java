package com.hengyi.japp.common.data;

public enum BindCorporationType {
	LOCAL("LOCAL"), HR("HR"), SAP("SAP"), OA1("OA"), OA2("OA");
	private final String name;

	private BindCorporationType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
