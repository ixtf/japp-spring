package com.hengyi.japp.common.service;

import com.hengyi.japp.common.sap.destination.DestinationType;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.server.JCoServer;

public interface CommonSapService {
	JCoDestination getDestination() throws Exception;

	JCoDestination getDestination(DestinationType type) throws Exception;

	JCoRepository getRepository() throws Exception;

	JCoRepository getRepository(DestinationType type) throws Exception;

	JCoFunction getFunction(String fName) throws Exception;

	JCoFunction getFunction(String fName, DestinationType type)
			throws Exception;

	void execute(JCoFunction function) throws Exception;

	void execute(JCoFunction function, DestinationType type) throws Exception;

	void execute(JCoFunction function, JCoDestination dest) throws Exception;

	JCoTable findAllDomvalue(String ysPackTypeDomvalue) throws Exception;

	JCoServer getServer() throws Exception;

	JCoServer getServer(DestinationType type) throws Exception;
}
