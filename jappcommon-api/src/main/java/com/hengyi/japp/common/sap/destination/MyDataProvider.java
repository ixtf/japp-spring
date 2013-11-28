package com.hengyi.japp.common.sap.destination;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.sap.conn.jco.ext.DataProviderException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.ServerDataEventListener;
import com.sap.conn.jco.ext.ServerDataProvider;

public class MyDataProvider implements DestinationDataProvider,
		ServerDataProvider {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private final Map<String, Properties> secureDBStorage;

	public MyDataProvider() {
		ImmutableMap.Builder<String, Properties> builder = ImmutableMap
				.builder();
		for (DestinationType type : DestinationType.values())
			builder.put(type.name(), getProperties(type.getFileName()));
		secureDBStorage = builder.build();
	}

	private Properties getProperties(String path) {
		try {
			InputStream in = MyDataProvider.class.getResourceAsStream(path);
			Properties p = new Properties();
			p.load(in);
			return p;
		} catch (FileNotFoundException e) {
			log.error("{}，文件不存在！", path);
			throw new Error(e);
		} catch (IOException e) {
			log.error("{}，文件读取有误！", path);
			throw new Error(e);
		}
	}

	@Override
	public Properties getDestinationProperties(String destinationName) {
		try {
			Properties p = secureDBStorage.get(destinationName);
			if (p == null || p.isEmpty())
				throw new DataProviderException(
						DataProviderException.Reason.INVALID_CONFIGURATION,
						"destination configuration is incorrect", null);
			return p;
		} catch (RuntimeException re) {
			throw new DataProviderException(
					DataProviderException.Reason.INTERNAL_ERROR, re);
		}
	}

	@Override
	public void setDestinationDataEventListener(DestinationDataEventListener eL) {
	}

	@Override
	public boolean supportsEvents() {
		return false;
	}

	@Override
	public Properties getServerProperties(String serverName) {
		return getDestinationProperties(serverName);
	}

	@Override
	public void setServerDataEventListener(ServerDataEventListener eL) {
	}

}
