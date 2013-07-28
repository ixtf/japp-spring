package com.hengyi.japp.personalevaluation.domain.data;

public enum PersonStatus {
	INIT("初始化"), SAVE("已保存"), SUBMIT("已提交");
	private final String name;

	private PersonStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
