package com.hengyi.japp.foreign.domain.data;

public enum Status {
	INIT("初始"), APART_FINISH("部分完成"), ALL_FINISH("全部完成");
	private final String name;

	private Status(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
