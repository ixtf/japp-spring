package com.hengyi.japp.report.domain.finereport;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.finereport.FineReportService;

@NodeEntity
public class FineReport extends Report {
	private static final long serialVersionUID = -5182667801659880331L;
	@Indexed(unique = true)
	private String cpt;

	@NotBlank
	public String getCpt() {
		return cpt;
	}

	public void setCpt(String cpt) {
		this.cpt = StringUtils.trim(cpt);
	}

	@Override
	public String getUrl() {
		WebApplicationContext context = ContextLoader
				.getCurrentWebApplicationContext();
		FineReportService fineReportService = context
				.getBean(FineReportService.class);
		return fineReportService.getUrl(this);
	}
}
