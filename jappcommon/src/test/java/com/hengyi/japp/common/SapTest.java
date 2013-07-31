package com.hengyi.japp.common;

import org.junit.Test;

import com.hengyi.japp.common.sap.destination.DestinationType;
import com.hengyi.japp.common.sap.destination.MyDestinationDataProvider;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.Environment;

public class SapTest {
	@Test
	public void test() throws JCoException {
		MyDestinationDataProvider d = new MyDestinationDataProvider();
		Environment.registerDestinationDataProvider(d);
		JCoDestination dest = JCoDestinationManager
				.getDestination(DestinationType.DEV.name());
		System.out.println(dest.getAttributes());
		dest = JCoDestinationManager.getDestination(DestinationType.PRO.name());
		System.out.println(dest.getAttributes());
	}
}
