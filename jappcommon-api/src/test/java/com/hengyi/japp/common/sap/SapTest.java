package com.hengyi.japp.common.sap;

import com.hengyi.japp.common.sap.destination.DestinationType;
import com.hengyi.japp.common.sap.destination.MyDestinationDataProvider;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.Environment;

public class SapTest {
	public static void main(String[] args) throws JCoException {
		Environment
				.registerDestinationDataProvider(new MyDestinationDataProvider());
		JCoDestination dest = JCoDestinationManager
				.getDestination(DestinationType.PRO.name());
	}
}
