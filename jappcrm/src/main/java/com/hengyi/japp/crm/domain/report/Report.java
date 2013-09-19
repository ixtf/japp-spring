package com.hengyi.japp.crm.domain.report;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.crm.data.ReportField;
import com.hengyi.japp.crm.domain.Indicator;

@NodeEntity
public class Report extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 3491953091603455779L;
	private static final String REPORT_INDICATOR = "REPORT_INDICATOR";
	@Indexed
	private String name;
	private Set<ReportField> fields;
	@RelatedTo(type = REPORT_INDICATOR)
	@Fetch
	private Set<Indicator> indicators;

	public Iterable<ReportField> getFields() {
		return fields;
	}

	public void setFields(Iterable<ReportField> fields) {
		this.fields = Sets.newHashSet(fields);
	}

	public Iterable<Indicator> getIndicators() {
		if (indicators == null)
			indicators = Sets.newHashSet();
		return indicators;
	}

	public void setIndicators(Iterable<Indicator> indicators) {
		this.indicators = Sets.newHashSet(indicators);
	}
}
