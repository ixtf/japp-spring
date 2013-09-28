package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.storage.StorageReport;
import com.hengyi.japp.crm.service.StorageReportService;

@Named
@Transactional
public class StorageReportServiceImpl extends ReportServiceImpl<StorageReport>
		implements StorageReportService {

	@Override
	public StorageReport findOne(Long nodeId) {
		return storageReportRepository.findOne(nodeId);
	}

	@Override
	public List<StorageReport> findAll() {
		return Lists.newArrayList(storageReportRepository.findAll());
	}
}
