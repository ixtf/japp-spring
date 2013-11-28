package com.hengyi.japp.report.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@RelationshipEntity
public class ReportAccess extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 2084665156805582994L;
	public static final String RELATIONSHIP = "REPORT_ACCESS";
	@StartNode
	private Operator operator;
	@EndNode
	private Report report;
	private int accessCount;
	private Date lastAccessTime;

	public void addAccessCount() {
		accessCount++;
		lastAccessTime = new Date();
	}

	public Operator getOperator() {
		return operator;
	}

	public Report getReport() {
		return report;
	}

	public int getAccessCount() {
		return accessCount;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
}
