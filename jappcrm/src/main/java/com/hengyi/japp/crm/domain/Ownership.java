package com.hengyi.japp.crm.domain;

public enum Ownership {
	SATE_COUNCIL("国务院主管企业"), SINO_FOREIGN("中外合作企业"), FOREIGN_JOINT("外商合资企业"), FOREIGN(
			"外商独资企业"), GOVERNMENT_INSTITUTION("政府、事业单位"), PRIVATE_TOWNSHIP(
			"民营、乡镇企业");
	private final String name;

	private Ownership(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
