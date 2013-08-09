package com.hengyi.japp.common.sap.destination;

public enum DestinationType {
	PRO("sap_pro.properties"), EQ("sap_eq.properties"), DEV(
			"sap_dev.properties");
	private final String fileName;

	private DestinationType(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
}
