package com.hengyi.japp.personalevaluation.domain.data;

public enum CompareType {
	GT("大于"), GTOREG("大于等于"), EQ("等于");
	private final String name;

	private CompareType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
