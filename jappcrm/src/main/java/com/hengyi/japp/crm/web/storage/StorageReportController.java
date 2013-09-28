package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.storage.StorageReport;
import com.hengyi.japp.crm.service.ReportService;
import com.hengyi.japp.crm.service.storage.StorageReportService;
import com.hengyi.japp.crm.web.ReportController;

@Named
@Scope("view")
public class StorageReportController extends ReportController<StorageReport>
		implements Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	@Inject
	private StorageReportService storageReportService;

	@Override
	protected ReportService<StorageReport> getReportService() {
		return storageReportService;
	}
}
