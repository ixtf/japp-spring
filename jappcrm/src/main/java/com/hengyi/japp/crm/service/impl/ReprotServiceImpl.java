package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.report.Report;
import com.hengyi.japp.crm.domain.repository.ReportRepository;
import com.hengyi.japp.crm.service.ReprotService;

@Named
@Transactional
public class ReprotServiceImpl implements ReprotService {
	@Inject
	private ReportRepository reportRepository;

	@Override
	public Report findOne(Long nodeId) {
		return reportRepository.findOne(nodeId);
	}

	@Override
	public void save(Report report) {
		reportRepository.save(report);
	}

	@Override
	public void delete(Report report) {
		reportRepository.delete(report);
	}

	@Override
	public List<Report> findAll() {
		return Lists.newArrayList(reportRepository.findAll());
	}
}
