package com.hengyi.japp.personalevaluation.domain.data;

public enum TaskStatus {
	INIT("初始化"), ACTIVE("活动中"), FINISH("已完成");
	private final String name;

	private TaskStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
