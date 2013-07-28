package com.hengyi.japp.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

public class DestinationDataProvider1 implements DestinationDataProvider {
	protected Logger log = LoggerFactory.getLogger(getClass());
	private Properties properties = getDefaultProperties();
	private final String propertiesUrl = "classpath:sap.properties";

	@Override
	public Properties getDestinationProperties(String arg0) {
		return getProperties();
	}

	public Properties getProperties() {
		return properties;
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

	@Override
	public void setDestinationDataEventListener(
			DestinationDataEventListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsEvents() {
		// TODO Auto-generated method stub
		return false;
	}

}
