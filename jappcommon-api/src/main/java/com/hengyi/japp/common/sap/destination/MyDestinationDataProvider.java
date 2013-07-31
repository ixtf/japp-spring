package com.hengyi.japp.common.sap.destination;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.google.common.collect.ImmutableMap;
import com.sap.conn.jco.ext.DataProviderException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

public class MyDestinationDataProvider implements DestinationDataProvider {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private final Map<String, Properties> secureDBStorage;

	public MyDestinationDataProvider() {
		ImmutableMap.Builder<String, Properties> builder = ImmutableMap
				.builder();
		builder.put(DestinationType.DEV.name(),
				getProperties("classpath:sap_dev.properties"));
		builder.put(DestinationType.EQ.name(),
				getProperties("classpath:sap_eq.properties"));
		builder.put(DestinationType.PRO.name(),
				getProperties("classpath:sap_pro.properties"));
		secureDBStorage = builder.build();
	}

	private Properties getProperties(String path) {
		try {
			File file = ResourceUtils.getFile(path);
			FileReader reader = new FileReader(file);
			Properties p = new Properties();
			p.load(reader);
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
			if (p != null) {
				if (p.isEmpty())
					throw new DataProviderException(
							DataProviderException.Reason.INVALID_CONFIGURATION,
							"destination configuration is incorrect", null);
				return p;
			}
		} catch (RuntimeException re) {
			throw new DataProviderException(
					DataProviderException.Reason.INTERNAL_ERROR, re);
		}
		return null;
	}

	@Override
	public void setDestinationDataEventListener(DestinationDataEventListener eL) {
	}

	@Override
	public boolean supportsEvents() {
		return false;
	}

}
