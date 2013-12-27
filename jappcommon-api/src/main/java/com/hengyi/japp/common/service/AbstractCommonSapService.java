package com.hengyi.japp.common.service;

import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.hengyi.japp.common.sap.Constant;
import com.hengyi.japp.common.sap.annotation.FunctionHandler;
import com.hengyi.japp.common.sap.destination.DestinationType;
import com.hengyi.japp.common.sap.destination.MyDataProvider;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.server.DefaultServerHandlerFactory.FunctionHandlerFactory;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

public abstract class AbstractCommonSapService implements CommonSapService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	public AbstractCommonSapService() throws Exception {
		super();
		if (!Environment.isDestinationDataProviderRegistered()) {
			MyDataProvider dataProvider = new MyDataProvider();

			System.out.println(this.getClass().getName());
			System.out.println("========注册SAP DESTINATION========");
			Environment.registerDestinationDataProvider(dataProvider);

			// System.out.println("========注册SAP SERVER========");
			// Environment.registerServerDataProvider(dataProvider);
			//
			// System.out.println("========启动SAP SERVER========");
			// for (DestinationType type : DestinationType.values()) {
			// JCoServer server = getServer(type);
			// server.setCallHandlerFactory(new FunctionHandlerFactory());
			// server.start();
			// }
		} else {
			System.out.println(this.getClass().getName());
			System.out.println("========已经注册SAP DATAPROVIDER========");
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
		execute(function, getDestination());
	}

	@Override
	public void execute(JCoFunction function, DestinationType type)
			throws Exception {
		execute(function, getDestination(type));
	}

	@Override
	public void execute(JCoFunction function, JCoDestination dest)
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
			log.error("exceptions:");
			for (Exception e : exceptions)
				log.error("", e);
			throw new Exception();
		}
	}

	@Override
	public JCoDestination getDestination() throws Exception {
		return getDestination(DestinationType.PRO);
	}

	@Override
	public JCoDestination getDestination(DestinationType type) throws Exception {
		try {
			return JCoDestinationManager.getDestination(type.name());
		} catch (JCoException e) {
			log.error("获取SAP DESTINATION出错", e);
			throw new Exception("ERROR.SAP", e);
		}
	}

	@Override
	public JCoRepository getRepository() throws Exception {
		return getRepository(DestinationType.PRO);
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
		return getFunction(fName, DestinationType.PRO);
	}

	@Override
	public JCoFunction getFunction(String fName, DestinationType type)
			throws Exception {
		try {
			return getRepository(type).getFunction(fName);
		} catch (JCoException e) {
			log.error("获取SAP FUNCTION出错：{}", fName);
			log.error("", e);
			throw new Exception("ERROR.SAP", e);
		}
	}

	protected void registerFunctionHandler(ApplicationContext context)
			throws Exception {
		registerFunctionHandler(context, DestinationType.PRO);
	}

	public void registerFunctionHandler(ApplicationContext context,
			DestinationType type) throws Exception {
		JCoServer server = getServer(type);
		FunctionHandlerFactory factory = (FunctionHandlerFactory) server
				.getCallHandlerFactory();
		if (factory == null) {
			factory = new FunctionHandlerFactory();
			server.setCallHandlerFactory(factory);
		}
		for (Entry<String, JCoServerFunctionHandler> entry : context
				.getBeansOfType(JCoServerFunctionHandler.class).entrySet()) {
			JCoServerFunctionHandler handler = entry.getValue();
			FunctionHandler annotation = handler.getClass().getAnnotation(
					FunctionHandler.class);
			if (annotation != null)
				factory.registerHandler(annotation.functionName(), handler);
		}
	}

	@Override
	public JCoServer getServer() throws Exception {
		return getServer(DestinationType.PRO);
	}

	@Override
	public JCoServer getServer(DestinationType type) throws Exception {
		try {
			return JCoServerFactory.getServer(type.name());
		} catch (JCoException e) {
			log.error("获取SAP SERVER出错", e);
			throw new Exception("ERROR.SAP", e);
		}
	}
}
