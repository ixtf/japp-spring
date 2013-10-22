package com.hengyi.japp.crm.web.model;

import java.util.Locale;

import com.hengyi.japp.crm.domain.CrmField;

public class CrmReportLineCrmFieldModel extends AbstractReportLine<CrmField>
		implements CrmReportLine<CrmField> {

	public CrmReportLineCrmFieldModel(CrmReportModel crmReport, CrmField data) {
		super(crmReport, data);
	}

	@Override
	public String getName() {
		return getData().getName();
	}

	@Override
	public String getName(Locale locale) {
		return getData().getName(locale);
	}

	@Override
	public Object getValue() {
		return getData().getValue(getCrm());
	}
}
