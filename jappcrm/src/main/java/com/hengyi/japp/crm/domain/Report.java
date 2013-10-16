package com.hengyi.japp.crm.domain;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.google.common.collect.Sets;

@NodeEntity
public class Report extends Modifiable implements Serializable {
	private static final long serialVersionUID = 3491953091603455779L;
	private static final String REPORT_CRMFIELD = "REPORT_CRMFIELD";
	private static final String REPORT_INDICATOR = "REPORT_INDICATOR";
	@Indexed
	@NotBlank
	private String name;
	@RelatedTo(type = REPORT_CRMFIELD)
	@Fetch
	private Set<CrmField> crmFields;
	@RelatedTo(type = REPORT_INDICATOR)
	@Fetch
	private Set<Indicator> indicators;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public Iterable<CrmField> getCrmFields() {
		if (crmFields == null)
			crmFields = Sets.newHashSet();
		return crmFields;
	}

	public void setCrmFields(Iterable<CrmField> crmFields) {
		this.crmFields = Sets.newHashSet(crmFields);
	}

	public Iterable<Indicator> getIndicators() {
		if (indicators == null)
			indicators = Sets.newHashSet();
		return indicators;
	}

	public void setIndicators(Iterable<Indicator> indicators) {
		this.indicators = Sets.newHashSet(indicators);
	}

	@Override
	public String toString() {
		return getName();
	}
}
