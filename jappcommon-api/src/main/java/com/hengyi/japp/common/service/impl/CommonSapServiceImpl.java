package com.hengyi.japp.common.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.google.common.collect.Maps;
import com.hengyi.japp.common.sap.Constant;
import com.hengyi.japp.common.sap.annotation.SapHandler;
import com.hengyi.japp.common.sap.destination.DestinationType;
import com.hengyi.japp.common.sap.destination.MyDestinationDataProvider;
import com.hengyi.japp.common.service.CommonSapService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

public abstract class CommonSapServiceImpl implements CommonSapService {
	protected Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private ApplicationContext context;

	public CommonSapServiceImpl() {
		super();
		if (!Environment.isDestinationDataProviderRegistered()) {
			System.out.println(this.getClass().getName());
			System.out.println("========注册SAP DESTINATION========");
			Environment
					.registerDestinationDataProvider(new MyDestinationDataProvider());
		} else {
			System.out.println(this.getClass().getName());
			System.out.println("========已经注册SAP DESTINATION========");
		}
	}

	@Override
	public JCoTable findAllDomvalue(String I_DOMNAME) throws Exception {
		JCoFunction function = getFunction(Constant.FunctionName.ZFINDALL_DD_DOMVALUE);
		function.getImportParameterList().setValue("I_DOMNAME", I_DOMNAME);
		execute(function);
		return function.getTableParameterList().getTable("ET_DD07V");
	}

	@Override
	public void execute(JCoFunction function) throws Exception {
		execute(getDestination(), function);
	}

	@Override
	public void execute(DestinationType type, JCoFunction function)
			throws Exception {
		execute(getDestination(type), function);
	}

	@Override
	public void execute(JCoDestination dest, JCoFunction function)
			throws Exception {
		log.info("execute sap function：{}", function.getName());
		try {
			function.execute(dest);
		} catch (JCoException e) {
			log.error("sap function {} execute error!", function.getName());
			throw new Exception("ERROR.SAP.FUNCTION.EXECUTE:"
					+ function.getName(), e);
		}
		Exception[] exceptions = function.getExceptionList();
		if (exceptions != null) {
			log.error("functionName:{}", function.getName());
			log.error("importParameterList:{}",
					function.getImportParameterList());
			log.error("changingParameterList:{}",
					function.getChangingParameterList());
			log.error("tableParameterList:{}", function.getTableParameterList());
			log.error("exceptions:", exceptions);
			throw new Exception("ERROR.SAP.FUNCTION.EXECUTE"
					+ function.getName(), exceptions[0]);
		}
	}

	@Override
	public JCoDestination getDestination() throws Exception {
		try {
			return JCoDestinationManager.getDestination(DestinationType.PRO
					.name());
		} catch (JCoException e) {
			log.error("获取SAP SERVER出错", e);
			throw new Exception("ERROR.SAP", e);
		}
	}

	@Override
	public JCoDestination getDestination(DestinationType type) throws Exception {
		try {
			return JCoDestinationManager.getDestination(type.name());
		} catch (JCoException e) {
			log.error("获取SAP SERVER出错", e);
			throw new Exception("ERROR.SAP", e);
		}
	}

	@Override
	public JCoRepository getRepository() throws Exception {
		try {
			return getDestination().getRepository();
		} catch (JCoException e) {
			log.error("获取SAP REPOSITORY出错", e);
			throw new Exception("ERROR.SAP", e);
		}
	}

	@Override
	public JCoRepository getRepository(DestinationType type) throws Exception {
		try {
			return getDestination(type).getRepository();
		} catch (JCoException e) {
			log.error("获取SAP REPOSITORY出错", e);
			throw new Exception("ERROR.SAP", e);
		}
	}

	@Override
	public JCoFunction getFunction(String fName) throws Exception {
		try {
			return getRepository().getFunction(fName);
		} catch (JCoException e) {
			log.error("获取SAP FUNCTION出错：{}", fName, e);
			throw new Exception("ERROR.SAP", e);
		}
	}

	@Override
	public JCoFunction getFunction(DestinationType type, String fName)
			throws Exception {
		try {
			return getRepository(type).getFunction(fName);
		} catch (JCoException e) {
			log.error("获取SAP FUNCTION出错：{}", fName, e);
			throw new Exception("ERROR.SAP", e);
		}
	}

	// protected void registerServer() throws Exception {
	// Map<String, JCoServerFunctionHandler> map = getHandlerMap();
	// if (map == null || map.isEmpty())
	// return;
	// log.info("========注册SAP SERVER========");
	// Environment.registerServerDataProvider(this);
	// JCoServer server;
	// server = getServer();
	// FunctionHandlerFactory factory = new FunctionHandlerFactory();
	// for (Entry<String, JCoServerFunctionHandler> entry : map.entrySet()) {
	// factory.registerHandler(entry.getKey(), entry.getValue());
	// log.info("========注册SAP HANDLER FUNCTION_NAME：{}========",
	// entry.getKey());
	// }
	// server.setCallHandlerFactory(factory);
	// log.info("========启动SAP SERVER========");
	// server.start();
	// }

	protected Map<String, JCoServerFunctionHandler> getHandlerMap() {
		Map<String, JCoServerFunctionHandler> map = Maps.newHashMap();
		for (Entry<String, JCoServerFunctionHandler> entry : context
				.getBeansOfType(JCoServerFunctionHandler.class).entrySet()) {
			JCoServerFunctionHandler handler = entry.getValue();
			SapHandler annotation = handler.getClass().getAnnotation(
					SapHandler.class);
			if (annotation != null)
				map.put(annotation.functionName(), handler);
		}
		return map;
	}

	@Override
	public JCoServer getServer() throws Exception {
		try {
			return JCoServerFactory.getServer(null);
		} catch (JCoException e) {
			log.error("获取SAP SERVER出错", e);
			throw new Exception("ERROR.SAP", e);
		}
	}
}
