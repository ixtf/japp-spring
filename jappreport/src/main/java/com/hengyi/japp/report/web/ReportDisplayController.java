package com.hengyi.japp.report.web;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.web.model.ReportModel;

//报表显示无tab，单张显示
@Named
@Scope("view")
public class ReportDisplayController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 2953131553375376983L;
	private Long nodeId;
	private ReportModel reportModel;

	public ReportModel getReportModel() {
		if (reportModel != null)
			return reportModel;
		Report report = reportRepository.findOne(nodeId);
		MyUtil.checkAuthorization(report);
		String url = reportFactory.reportService(report).getUrl(report);
		reportModel = new ReportModel(report, url);
		return reportModel;
	}

	// 搜索框选择报表
	public void selectReport(SelectEvent event) {
		Report report = (Report) event.getObject();
		try {
			MyUtil.checkAuthorization(report);
			redirect("/reports/" + report.getNodeId() + "/display");
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	// 菜单栏点选报表
	public void menuAction(ActionEvent event) {
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
