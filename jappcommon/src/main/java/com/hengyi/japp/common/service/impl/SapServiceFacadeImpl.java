package com.hengyi.japp.common.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.hengyi.japp.common.sap.annotation.SapHandler;
import com.hengyi.japp.common.sap.service.impl.SapServiceImpl;
import com.hengyi.japp.common.service.SapServiceFacade;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

@Service
public class SapServiceFacadeImpl extends SapServiceImpl implements
		SapServiceFacade {
	@Resource
	private ApplicationContext context;

	// @Override
	// public <K, V> Map<K, V> findAllDomvalue(String I_DOMNAME, Class<K> K,
	// Class<V> V) throws Exception {
	// ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
	// JCoTable table = findAllDomvalue(I_DOMNAME);
	// if (!table.isEmpty()) {
	// do {
	// builder.put((K) table.getValue("DOMVALUE_L"),
	// (V) table.getValue("DDTEXT"));
	// } while (table.nextRow());
	// }
	// return builder.build();
	// }

	@Override
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

	@PostConstruct
	private void start() throws Exception {
		registerDestination();
		registerServer();
	}
}
