package com.hengyi.japp.crm.domain;

import java.util.Locale;

import javax.validation.constraints.NotNull;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.crm.MessageUtil;
import com.hengyi.japp.crm.data.CrmFieldType;
import com.hengyi.japp.crm.web.model.CrmReportLineModel;

@NodeEntity
@SuppressWarnings("unchecked")
public class CrmField extends AbstractNeo4j implements
		CrmReportLineModel<CrmField> {
	private static final long serialVersionUID = 5805479477891553551L;
	private static Logger log = LoggerFactory.getLogger(CrmField.class);
	@NotNull
	private CrmFieldType crmFieldType;
	@NotBlank
	@Indexed(unique = true)
	private String fieldName;
	@NotBlank
	private String displayName;

	public CrmFieldType getCrmFieldType() {
		return crmFieldType;
	}

	public void setCrmFieldType(CrmFieldType crmFieldType) {
		this.crmFieldType = crmFieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public <T> T getValue(Crm crm) {
		try {
			return (T) PropertyUtils.getProperty(crm, getFieldName());
		} catch (Exception e) {
			log.error(crm + "-" + getFieldName(), e);
			return null;
		}
	}

	@Override
	public String getReportLineName() {
		return getReportLineName(Locale.CHINA);
	}

	@Override
	public String getReportLineName(Locale locale) {
		return MessageUtil.get(getDisplayName());
	}

	@Override
	public Object getReportLineValue(Crm crm) {
		try {
			return PropertyUtils.getProperty(crm, getFieldName());
		} catch (Exception e) {
			log.error(crm + "-" + getFieldName(), e);
			return null;
		}
	}

	@Override
	public CrmField getReportLineData() {
		return this;
	}
}
