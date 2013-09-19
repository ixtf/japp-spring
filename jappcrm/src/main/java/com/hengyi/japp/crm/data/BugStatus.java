package com.hengyi.japp.crm.data;

public enum BugStatus {
	S1("name"), S2("");
	private final String name;

	private BugStatus(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
