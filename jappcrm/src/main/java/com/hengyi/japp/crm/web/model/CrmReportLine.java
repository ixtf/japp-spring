package com.hengyi.japp.crm.web.model;

public interface CrmReportLine<T> {
	String getName();

	Object getValue();

	T getData();
}
