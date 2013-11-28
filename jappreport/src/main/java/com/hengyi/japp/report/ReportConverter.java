package com.hengyi.japp.report;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.web.jsf.FacesContextUtils;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.report.domain.Report;

public class ReportConverter implements Converter {
	private Neo4jOperations template;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) {
		if (template == null)
			template = FacesContextUtils.getWebApplicationContext(facesContext)
					.getBean(Neo4jOperations.class);
		if (StringUtils.isBlank(value))
			return null;
		try {
			return template.findOne(Long.valueOf(value), Report.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value instanceof AbstractNeo4j)
			return ((AbstractNeo4j) value).getNodeId().toString();
		else
			return null;
	}

}
