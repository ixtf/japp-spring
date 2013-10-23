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

@NodeEntity
@SuppressWarnings("unchecked")
public class CrmField extends AbstractNeo4j {
    private static final long serialVersionUID = 5805479477891553551L;
    private static Logger log = LoggerFactory.getLogger(CrmField.class);
    @NotNull
    @Indexed
    private CrmFieldType crmFieldType;
    @NotBlank
    @Indexed(unique = true)
    private String fieldName;
    @NotBlank
    private String displayName;

    public CrmField() {
	super();
    }

    public CrmField(CrmFieldType crmFieldType, String fieldName,
	    String displayName) {
	super();
	this.crmFieldType = crmFieldType;
	this.fieldName = fieldName;
	this.displayName = displayName;
    }

    public CrmField(String fieldName, String displayName) {
	this(CrmFieldType.CRM, fieldName, displayName);
    }

    public CrmField(String fieldName) {
	this(fieldName, fieldName);
    }

    public CrmField(CrmFieldType crmFieldType, String fieldName) {
	this(crmFieldType, fieldName, fieldName);
    }

    public String getName() {
	return getName(Locale.CHINA);
    }

    public String getName(Locale locale) {
	return MessageUtil.get(getDisplayName(), locale);
    }

    public <T> T getValue(Crm crm) {
	try {
	    return (T) PropertyUtils.getProperty(crm, getFieldName());
	} catch (Exception e) {
	    log.error(crm + "-" + getFieldName(), e);
	    return null;
	}
    }

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
}
