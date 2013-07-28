package com.hengyi.japp.common;

import org.junit.Test;

import com.sap.conn.jco.ext.Environment;

public class SapTest {
	@Test
	public void test() {
		System.out.println("tom");
		Environment
				.registerDestinationDataProvider(new DestinationDataProvider2());
		// Environment
		// .registerDestinationDataProvider(new DestinationDataProvider1());
	}
}
