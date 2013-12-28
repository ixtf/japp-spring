package com.hengyi.japp.common.service.impl;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.context.ApplicationContext;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.dto.HrOrganizationDTO;
import com.hengyi.japp.common.service.AbstractCommonSapService;
import com.hengyi.japp.common.service.SapServiceFacade;

@Named
public class SapServiceFacadeImpl extends AbstractCommonSapService implements
		SapServiceFacade {
	public SapServiceFacadeImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Resource
	private ApplicationContext context;

	// @Override
	// protected Map<String, JCoServerFunctionHandler> getHandlerMap() {
	// Map<String, JCoServerFunctionHandler> map = Maps.newHashMap();
	// for (Entry<String, JCoServerFunctionHandler> entry : context
	// .getBeansOfType(JCoServerFunctionHandler.class).entrySet()) {
	// JCoServerFunctionHandler handler = entry.getValue();
	// FunctionHandler annotation = handler.getClass().getAnnotation(
	// FunctionHandler.class);
	// if (annotation != null)
	// map.put(annotation.functionName(), handler);
	// }
	// return map;
	// }

	@Override
	public Collection<HrOrganizationDTO> findAllOrganization(String empSn) {
		Set<HrOrganizationDTO> result = Sets.newHashSet();
		// TODO
		return result;
	}
}
