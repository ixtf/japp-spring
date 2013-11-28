package com.hengyi.japp.common;

import org.junit.Test;

import com.hengyi.japp.common.sap.destination.DestinationType;
import com.hengyi.japp.common.sap.destination.MyDataProvider;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.Environment;

public class SapTest {
	@Test
	public void test() throws JCoException {
		MyDataProvider d = new MyDataProvider();
		Environment.registerDestinationDataProvider(d);
		JCoDestination dest = JCoDestinationManager
				.getDestination(DestinationType.DEV.name());
		System.out.println(dest.getAttributes());
		dest = JCoDestinationManager.getDestination(DestinationType.PRO.name());
		System.out.println(dest.getAttributes());
	}
}
