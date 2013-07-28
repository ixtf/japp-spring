package com.hengyi.japp.common.sap.service;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.ServerDataProvider;
import com.sap.conn.jco.server.JCoServer;

public interface SapService extends DestinationDataProvider, ServerDataProvider {
	JCoServer getServer() throws Exception;

	JCoDestination getDestination() throws Exception;

	JCoRepository getRepository() throws Exception;

	JCoFunction getFunction(String fName) throws Exception;

	void execute(JCoFunction function) throws Exception;

	JCoTable findAllDomvalue(String ysPackTypeDomvalue) throws Exception;
}
