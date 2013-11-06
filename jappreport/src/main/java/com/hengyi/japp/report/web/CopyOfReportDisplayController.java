package com.hengyi.japp.report.web;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.shiro.SecurityUtils;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.web.model.ReportModel;

//@Named
//@Scope("session")
public class CopyOfReportDisplayController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 9146840004984249764L;
	private Map<Report, ReportModel> reportModelMap = Maps.newLinkedHashMap();
	// 当前正在浏览的报表
	private ReportModel reportModel;
	// 当前所有浏览的报表
	private List<ReportModel> reportModels = Lists.newArrayList(reportModelMap
			.values());
	// 收藏菜单
	private Submenu collectSubmenu;

	// 菜单栏点选报表
	public void menuAction(ActionEvent event) {
		Report report = (Report) event.getComponent().getAttributes()
				.get("report");
		addReport(report);
	}

	// 搜索框选择报表
	public void selectReport(SelectEvent event) throws Exception {
		// TODO 暂时解决当点登入
		if (!SecurityUtils.getSubject().hasRole("superUser"))
			return;
		Report report = (Report) event.getObject();
		addReport(report);
	}

	private void addReport(Report report) {
		String url = reportFactory.reportService(report).getUrl(report);
		if (!reportModelMap.containsKey(report)) {
			reportModel = new ReportModel(report, url);
			reportModelMap.put(report, reportModel);
			setChange();
		}
		reportModel = reportModelMap.get(report);
		redirect("/display");
	}

	public void onTabClose(TabCloseEvent event) {
		ReportModel closeReportModel = (ReportModel) event.getData();
		reportModelMap.remove(closeReportModel.getReport());
		setChange();
		if (reportModelMap.isEmpty())
			// 全部关闭返回首页
			redirect("/");
		else if (closeReportModel.equals(reportModel))
			// 当前页面被关闭，回到打开中的第一个报表
			reportModel = getReportModels().get(0);
	}

	public void onTabChange(TabChangeEvent event) {
		reportModel = (ReportModel) event.getData();
	}

	// 报表浏览变化
	public void setChange() {
		reportModels = Lists.newArrayList(reportModelMap.values());
		// 收藏菜单
		collectSubmenu = cacheService.getCollectSubmenu(reportModelMap);
	}

	public List<ReportModel> getReportModels() {
		return reportModels;
	}

	public Submenu getCollectSubmenu() {
		return collectSubmenu;
	}

	public ReportModel getReportModel() {
		return reportModel;
	}

	public int getActiveIndex() {
		if (reportModel != null)
			return getReportModels().indexOf(reportModel);
		return 0;
	}

	public void setCollectSubmenu(Submenu collectSubmenu) {
		this.collectSubmenu = collectSubmenu;
	}

	public void setActiveIndex(int activeIndex) {
		// System.out.println(activeIndex);
	}
}
