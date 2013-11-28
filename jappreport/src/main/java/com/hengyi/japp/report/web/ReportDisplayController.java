package com.hengyi.japp.report.web;

import java.io.Serializable;

import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.event.report.ReportAccessEvent;
import com.hengyi.japp.report.web.model.ReportModel;

//报表显示无tab，单张显示
@Named
@Scope("view")
public class ReportDisplayController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 2953131553375376983L;
	private Long nodeId;
	private Report report;
	private ReportModel reportModel;

	public void checkAuthorization() {
		try {
			report = reportRepository.findOne(nodeId);
			MyUtil.checkAuthorization(report);
		} catch (Exception e) {
			errorMessage(e, false);
			redirect("/");
		}
	}

	public ReportModel getReportModel() throws Exception {
		if (reportModel == null) {
			reportModel = new ReportModel(report, reportFactory);

			eventPublisher.publish(new ReportAccessEvent(report,
					getCurrentOperator()));
		}
		return reportModel;
	}

	// 搜索框选择报表
	public void selectReport(SelectEvent event) {
		report = (Report) event.getObject();
		try {
			MyUtil.checkAuthorization(report);
			redirect("/reports/" + report.getNodeId() + "/display");
		} catch (Exception e) {
			errorMessage(e, false);
		}
	}

	public void setReportModel(ReportModel reportModel) {
		this.reportModel = reportModel;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
}
