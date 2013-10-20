package com.hengyi.japp.common.data;

public enum CompareType {
	GT("大于"), GTOREQ("大于等于"), EQ("等于"), LTOREQ("小于等于"), LT("小于");
	private final String name;

	private CompareType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
