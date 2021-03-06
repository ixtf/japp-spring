package com.hengyi.japp.personalevaluation.service.impl;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.context.ApplicationContext;

import com.google.common.collect.Maps;
import com.hengyi.japp.common.sap.annotation.FunctionHandler;
import com.hengyi.japp.common.service.impl.CommonSapServiceImpl;
import com.hengyi.japp.personalevaluation.service.SapServiceFacade;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

@Named
public class SapServiceFacadeImpl extends CommonSapServiceImpl implements
		SapServiceFacade {
	@Resource
	private ApplicationContext context;

	@Override
	protected Map<String, JCoServerFunctionHandler> getHandlerMap() {
		Map<String, JCoServerFunctionHandler> map = Maps.newHashMap();
		for (Entry<String, JCoServerFunctionHandler> entry : context
				.getBeansOfType(JCoServerFunctionHandler.class).entrySet()) {
			JCoServerFunctionHandler handler = entry.getValue();
			FunctionHandler annotation = handler.getClass().getAnnotation(
					FunctionHandler.class);
			if (annotation != null)
				map.put(annotation.functionName(), handler);
		}
		return map;
	}
}
