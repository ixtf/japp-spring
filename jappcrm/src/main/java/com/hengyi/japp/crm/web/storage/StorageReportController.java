package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.storage.StorageReport;
import com.hengyi.japp.crm.service.ReportService;
import com.hengyi.japp.crm.service.storage.StorageReportService;
import com.hengyi.japp.crm.service.storage.StorageService;
import com.hengyi.japp.crm.web.ReportController;

@Named
@Scope("view")
public class StorageReportController extends ReportController<StorageReport>
		implements Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	@Inject
	private StorageService storageService;
	@Inject
	private StorageReportService storageReportService;
	private List<CrmField> allCrmFields;

	@Override
	protected ReportService<StorageReport> getReportService() {
		return storageReportService;
	}

	@Override
	public List<CrmField> getAllCrmFields() {
		if (allCrmFields == null)
			allCrmFields = storageService.findAllCrmField();
		return allCrmFields;
	}

	public void setAllCrmFields(List<CrmField> allCrmFields) {
		this.allCrmFields = allCrmFields;
	}
}
