package com.hengyi.japp.crm.data;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class CrmReportField implements Serializable {
	private static final long serialVersionUID = 718162184462560481L;
	private final CrmReport crmReport;
	private final CrmField crmField;

	public static List<CrmReportField> crmReportFields(CrmReport crmReport) {
		ImmutableList.Builder<CrmReportField> builder = ImmutableList.builder();
		for (CrmField crmField : crmReport.getReport().getCrmFields())
			builder.add(new CrmReportField(crmReport, crmField));
		return builder.build();
	}

	private CrmReportField(CrmReport crmReport, CrmField crmField) {
		super();
		this.crmReport = crmReport;
		this.crmField = crmField;
	}

	public CrmField getCrmField() {
		return crmField;
	}

	public Object getValue() throws Exception {
		return crmField.getValue(crmReport.getCrm());
	}

}
