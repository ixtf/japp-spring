package com.hengyi.japp.crm.web.model;

import java.util.Locale;

import com.hengyi.japp.crm.domain.Crm;

public interface CrmReportLineModel<T> {
	String getReportLineName();

	String getReportLineName(Locale locale);

	Object getReportLineValue(Crm crm);

	T getReportLineData();
}
