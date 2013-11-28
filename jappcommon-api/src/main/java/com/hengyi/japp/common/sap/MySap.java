package com.hengyi.japp.common.sap;

import com.hengyi.japp.common.sap.destination.DestinationType;
import com.hengyi.japp.common.sap.destination.MyDataProvider;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.server.JCoServer;

public final class MySap {
	private MySap() {
		super();
		if (Environment.isDestinationDataProviderRegistered())
			return;
		MyDataProvider dataProvider = new MyDataProvider();

		System.out.println(this.getClass().getName());
		System.out.println("========注册SAP DESTINATION========");
		Environment.registerDestinationDataProvider(dataProvider);

		System.out.println("========注册SAP SERVER========");
		Environment.registerServerDataProvider(dataProvider);

		System.out.println("========启动SAP SERVER========");
		for (DestinationType type : DestinationType.values()) {
			JCoServer server = getServer(type);
			server.start();
		}
	}

	private JCoServer getServer(DestinationType type) {
		// TODO Auto-generated method stub
		return null;
	}

	public static JCoDestination getDestination() throws Exception {
		return null;
	}
}
