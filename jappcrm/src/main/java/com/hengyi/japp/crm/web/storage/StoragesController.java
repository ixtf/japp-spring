package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.domain.storage.StorageReport;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.storage.StorageReportService;
import com.hengyi.japp.crm.service.storage.StorageService;
import com.hengyi.japp.crm.web.CrmsController;

@Named
@Scope("view")
public class StoragesController extends CrmsController<Storage> implements
		Serializable {
	private static final long serialVersionUID = 3708518912737819900L;
	@Inject
	private StorageService storageService;
	@Inject
	private StorageReportService storageReportService;

	@Override
	protected CrmService<Storage> getCrmService() {
		return storageService;
	}

	@Override
	protected List<StorageReport> getReports() {
		return storageReportService.findAll();
	}
}
