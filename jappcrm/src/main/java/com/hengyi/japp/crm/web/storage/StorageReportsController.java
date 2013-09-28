package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.storage.StorageReport;
import com.hengyi.japp.crm.service.ReportService;
import com.hengyi.japp.crm.web.ReportsController;

@Named
@Scope("view")
public class StorageReportsController extends ReportsController<StorageReport>
		implements Serializable {
	private static final long serialVersionUID = 7250376086420104890L;

	@Override
	public void edit() {
		redirect(urlUtil.getStorageReportsPath() + "/"
				+ getReport().getNodeId());
	}

	@Override
	protected ReportService<StorageReport> getReportService() {
		return storageReportService;
	}
}
