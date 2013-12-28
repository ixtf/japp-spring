package com.hengyi.japp.common.sap;

import com.hengyi.japp.common.service.AbstractCommonSapService;

public class SapTest extends AbstractCommonSapService {

	public SapTest() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
//		System.loadLibrary("sapjco3");
		SapTest test = new SapTest();
		System.out.println(test.getDestination());
	}
}
