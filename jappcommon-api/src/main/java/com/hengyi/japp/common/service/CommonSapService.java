package com.hengyi.japp.common.service;

import com.hengyi.japp.common.sap.destination.DestinationType;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.server.JCoServer;

public interface CommonSapService {
	JCoServer getServer() throws Exception;

	JCoDestination getDestination() throws Exception;

	JCoDestination getDestination(DestinationType type) throws Exception;

	JCoRepository getRepository() throws Exception;

	JCoRepository getRepository(DestinationType type) throws Exception;

	JCoFunction getFunction(String fName) throws Exception;

	JCoFunction getFunction(DestinationType type, String fName)
			throws Exception;

	void execute(JCoFunction function) throws Exception;

	void execute(DestinationType type, JCoFunction function) throws Exception;

	void execute(JCoDestination dest, JCoFunction function) throws Exception;

	JCoTable findAllDomvalue(String ysPackTypeDomvalue) throws Exception;
}
