package com.hengyi.japp.crm;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.crm.domain.Certificate;

public class CertificateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) {
		WebApplicationContext webContext = FacesContextUtils
				.getWebApplicationContext(facesContext);
		Neo4jOperations template = webContext.getBean(Neo4jOperations.class);
		return template.findOne(Long.valueOf(value), Certificate.class);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		return ((AbstractNeo4j) value).getNodeId().toString();
	}

}
