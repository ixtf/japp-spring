package com.hengyi.japp.report;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.finereport.FineReport;
import com.hengyi.japp.report.domain.repository.ReportRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@ActiveProfiles("dev")
@Transactional
public class TestService {
	@Resource
	protected ReportRepository reportRepository;

	@Test
	public void test() {
		for (Report report : reportRepository.findAllByQuery(
				FineReport.class.getSimpleName(), "name", "*产*")) {
			System.out.println(report);
		}
	}
}
