package com.hengyi.japp.report.domain.finereport;

public enum OpType {
	DEFAULT("默认", ""), VIEW("分析", "view"), WRITE("填报", "write");
	private final String name;
	private final String value;

	private OpType(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getName();
	}
}
