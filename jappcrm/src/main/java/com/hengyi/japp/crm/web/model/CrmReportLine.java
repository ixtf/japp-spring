package com.hengyi.japp.crm.web.model;

import java.util.Locale;

public interface CrmReportLine<T> {
	String getName();

	String getName(Locale locale);

	Object getValue();

	T getData();
}
