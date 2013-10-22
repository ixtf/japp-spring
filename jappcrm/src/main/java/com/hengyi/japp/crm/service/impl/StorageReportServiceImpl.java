package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.repository.StorageReportRepository;
import com.hengyi.japp.crm.domain.storage.StorageReport;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.storage.StorageReportService;
import com.hengyi.japp.crm.service.storage.StorageService;

@Named("storageReportService")
@Transactional
@SuppressWarnings("unchecked")
public class StorageReportServiceImpl extends ReportServiceImpl<StorageReport>
		implements StorageReportService {
	@Inject
	private StorageService storageService;

	@Resource
	protected StorageReportRepository storageReportRepository;

	@Override
	public StorageReport newReport() {
		return new StorageReport();
	}

	@Override
	protected CrmService<?> getCrmService() {
		return storageService;
	}

	@Override
	public <R extends Repository<StorageReport, Long>> R getRepository() {
		return (R) storageReportRepository;
	}
}
