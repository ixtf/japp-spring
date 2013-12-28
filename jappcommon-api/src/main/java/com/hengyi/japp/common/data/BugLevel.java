package com.hengyi.japp.common.data;

public enum BugLevel {
	L1("name"), L2("");
	private final String name;

	private BugLevel(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
