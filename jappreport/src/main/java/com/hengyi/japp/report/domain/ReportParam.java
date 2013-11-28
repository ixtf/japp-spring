package com.hengyi.japp.report.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class ReportParam extends AbstractNeo4j implements Serializable,
		Comparable<ReportParam> {
	private static final long serialVersionUID = 7434381694975585207L;
	// @RelatedTo(type = Report.PARAM, direction = Direction.INCOMING)
	// @Fetch
	// private Report report;
	@NotBlank
	private String key;
	@NotBlank
	private String value;

	// public ReportParam() {
	// super();
	// }
	//
	// public ReportParam(Report report) {
	// super();
	// this.report = report;
	// }
	//
	// public Report getReport() {
	// return report;
	// }
	//
	// public void setReport(Report report) {
	// this.report = report;
	// }

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = StringUtils.trim(key);
	}

	public void setValue(String value) {
		this.value = StringUtils.trim(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ReportParam other = (ReportParam) o;
		return Objects.equal(getKey(), other.getKey());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getKey());
	}

	@Override
	public String toString() {
		return getKey() + "=" + getValue();
	}

	@Override
	public int compareTo(ReportParam o) {
		return getKey().compareTo(o.getKey());
	}
}
