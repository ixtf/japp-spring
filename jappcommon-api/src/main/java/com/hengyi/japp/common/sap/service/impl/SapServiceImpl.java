package com.hengyi.japp.common.sap.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ResourceUtils;

import com.google.common.collect.Maps;
import com.hengyi.japp.common.sap.Constant;
import com.hengyi.japp.common.sap.annotation.SapHandler;
import com.hengyi.japp.common.sap.service.SapService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.ext.ServerDataEventListener;
import com.sap.conn.jco.server.DefaultServerHandlerFactory.FunctionHandlerFactory;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

public abstract class SapServiceImpl implements SapService {
	protected Logger log = LoggerFactory.getLogger(getClass());
	private Properties properties = getDefaultProperties();
	private final String propertiesUrl = "classpath:sap.properties";
	@Resource
	private ApplicationContext context;

	@Override
	public JCoTable findAllDomvalue(String I_DOMNAME) throws Exception {
		JCoFunction function = getFunction(Constant.FunctionName.ZFINDALL_DD_DOMVALUE);
		function.getImportParameterList().setValue("I_DOMNAME", I_DOMNAME);
		execute(function);
		return function.getTableParameterList().getTable("ET_DD07V");
	}

	@Override
	public void execute(JCoFunction function) throws Exception {
		log.info("execute sap function：{}", function.getName());
		try {
			function.execute(getDestination());
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
			// log.error("exceptions:", exceptions);
			throw new Exception("ERROR.SAP.FUNCTION.EXECUTE"
					+ function.getName(), exceptions[0]);
		}
	}

	protected void registerDestination() {
		log.info("========注册SAP DESTINATION========");
		Environment.registerDestinationDataProvider(this);
	}

	protected void registerServer() throws Exception {
		Map<String, JCoServerFunctionHandler> map = getHandlerMap();
		if (map == null || map.isEmpty())
			return;
		log.info("========注册SAP SERVER========");
		Environment.registerServerDataProvider(this);
		JCoServer server;
		server = getServer();
		FunctionHandlerFactory factory = new FunctionHandlerFactory();
		for (Entry<String, JCoServerFunctionHandler> entry : map.entrySet()) {
			factory.registerHandler(entry.getKey(), entry.getValue());
			log.info("========注册SAP HANDLER FUNCTION_NAME：{}========",
					entry.getKey());
		}
		server.setCallHandlerFactory(factory);
		log.info("========启动SAP SERVER========");
		server.start();
	}

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

	private Properties getDefaultProperties() {
		try {
			File file = ResourceUtils.getFile(propertiesUrl);
			FileReader reader = new FileReader(file);
			Properties p = new Properties();
			p.load(reader);
			return p;
		} catch (FileNotFoundException e1) {
			log.error("{}，文件不存在！", propertiesUrl);
		} catch (IOException e) {
			log.error("{}，文件读取有误！", propertiesUrl);
		}
		return null;
	}

	public String getDestinationName() {
		return getProperties().getProperty(JCO_REP_DEST);
	}

	public String getServerName() {
		return getProperties().getProperty(JCO_PROGID);
	}

	public Properties getProperties() {
		return properties;
	}

	protected void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public JCoServer getServer() throws Exception {
		try {
			return JCoServerFactory.getServer(getServerName());
		} catch (JCoException e) {
			log.error("获取SAP SERVER出错", e);
			throw new Exception("ERROR.SAP", e);
		}
	}

	@Override
	public JCoDestination getDestination() throws Exception {
		try {
			return JCoDestinationManager.getDestination(getDestinationName());
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
	public JCoFunction getFunction(String fName) throws Exception {
		try {
			return getRepository().getFunction(fName);
		} catch (JCoException e) {
			log.error("获取SAP FUNCTION出错：{}", fName, e);
			throw new Exception("ERROR.SAP", e);
		}
	}

	@Override
	public void setDestinationDataEventListener(
			DestinationDataEventListener arg0) {
	}

	@Override
	public boolean supportsEvents() {
		return false;
	}

	@Override
	public void setServerDataEventListener(ServerDataEventListener arg0) {
	}

	@Override
	public Properties getDestinationProperties(String arg0) {
		return getProperties();
	}

	@Override
	public Properties getServerProperties(String arg0) {
		return getProperties();
	}
}
