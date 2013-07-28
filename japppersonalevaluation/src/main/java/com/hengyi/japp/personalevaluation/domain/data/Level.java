package com.hengyi.japp.personalevaluation.domain.data;

public enum Level {
	L1("优秀"), L2("良好"), L3("中等"), L4("及格"), L5("不及格");
	private final String name;

	private Level(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
