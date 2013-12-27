package com.hengyi.japp.crm.web.model;

import com.hengyi.japp.crm.domain.CrmField;

public class CrmReportLineCrmFieldModel implements CrmReportLine<CrmField> {
	private final CrmField crmField;
	private final Object value;

	public CrmReportLineCrmFieldModel(CrmReport crmReport, CrmField crmField) {
		super();
		this.crmField = crmField;
		value = crmField.getValue(crmReport.getCrm());
	}

	@Override
	public CrmField getData() {
		return crmField;
	}

	@Override
	public String getName() {
		return getData().getName();
	}

	@Override
	public Object getValue() {
		return value;
	}
}
