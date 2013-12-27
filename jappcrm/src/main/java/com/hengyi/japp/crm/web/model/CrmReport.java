package com.hengyi.japp.crm.web.model;

import java.util.List;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Report;

public interface CrmReport {
	Crm getCrm();

	Report getReport();

	List<CrmReportLine<?>> getLines();

	List<CrmReportLine<CrmField>> getCrmFieldLines();

	List<CrmReportLineIndicator> getIndicatorLines();

	Double getTotalIndicatorScore();
}
