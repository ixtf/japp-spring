package com.hengyi.japp.trans.domain.data;

public enum Meins {
	KG("千克"), TO("吨");
	private final String name;

	private Meins(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
