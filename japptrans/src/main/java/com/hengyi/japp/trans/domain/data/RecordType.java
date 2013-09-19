package com.hengyi.japp.trans.domain.data;

public enum RecordType {
	SEND("发货"), RECIEVE("收货");
	private final String name;

	private RecordType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
