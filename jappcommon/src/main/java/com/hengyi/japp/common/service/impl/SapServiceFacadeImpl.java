package com.hengyi.japp.common.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hengyi.japp.common.dto.HrOrganizationDTO;
import com.hengyi.japp.common.sap.annotation.SapHandler;
import com.hengyi.japp.common.service.SapServiceFacade;
import com.hengyi.japp.common.service.impl.CommonSapServiceImpl;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

@Service
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
			SapHandler annotation = handler.getClass().getAnnotation(
					SapHandler.class);
			if (annotation != null)
				map.put(annotation.functionName(), handler);
		}
		return map;
	}

	@Override
	public Collection<HrOrganizationDTO> findAllOrganization(String empSn) {
		Set<HrOrganizationDTO> result = Sets.newHashSet();
		// TODO
		return result;
	}
}
