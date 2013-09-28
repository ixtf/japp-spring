package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.repository.StorageReportRepository;
import com.hengyi.japp.crm.domain.storage.StorageReport;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.storage.StorageReportService;
import com.hengyi.japp.crm.service.storage.StorageService;

@Named("storageReportService")
@Transactional
public class StorageReportServiceImpl extends ReportServiceImpl<StorageReport>
		implements StorageReportService {
	@Inject
	private StorageService storageService;

	@Resource
	protected StorageReportRepository storageReportRepository;

	@Override
	public StorageReport findOne(Long nodeId) {
		return storageReportRepository.findOne(nodeId);
	}

	@Override
	public List<StorageReport> findAll() {
		return Lists.newArrayList(storageReportRepository.findAll());
	}

	@Override
	public StorageReport newReport() {
		return new StorageReport();
	}

	@Override
	protected CrmService<?> getCrmService() {
		return storageService;
	}
}
