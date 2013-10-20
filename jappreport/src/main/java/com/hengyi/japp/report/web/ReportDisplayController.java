package com.hengyi.japp.report.web;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.web.model.ReportModel;

@Named
@Scope("session")
public class ReportDisplayController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 9146840004984249764L;
	private Map<Report, ReportModel> reportModelMap = Maps.newLinkedHashMap();
	// 当前浏览的报表
	private ReportModel reportModel;

	public void menuAction(ActionEvent event) {
		Report report = (Report) event.getComponent().getAttributes()
				.get("report");
		if (!reportModelMap.containsKey(report)) {
			reportModel = new ReportModel(report);
			reportModelMap.put(report, reportModel);
		}
		reportModel = reportModelMap.get(report);
		redirect("/display");
	}

	public void onTabClose(TabCloseEvent event) {
		ReportModel closeReportModel = (ReportModel) event.getData();
		reportModelMap.remove(closeReportModel.getReport());
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

	public List<ReportModel> getReportModels() {
		return Lists.newArrayList(reportModelMap.values());
	}

	public int getActiveIndex() {
		if (reportModel != null)
			return getReportModels().indexOf(reportModel);
		return 0;
	}

	public void setActiveIndex(int activeIndex) {
		// System.out.println(activeIndex);
	}

	// private Cache<Report, ReportModel> reportModelCache = CacheBuilder
	// .newBuilder().maximumSize(2)
	// .expireAfterWrite(1000, TimeUnit.SECONDS).build();
	//
	// public void menuAction(ActionEvent event) {
	// try {
	// final Report report = (Report) event.getComponent().getAttributes()
	// .get("report");
	// reportModel = reportModelCache.get(report,
	// new Callable<ReportModel>() {
	// @Override
	// public ReportModel call() throws Exception {
	// // reportModel = new ReportModel(report);
	// // reportModels.add(reportModel);
	// return new ReportModel(report);
	// }
	// });
	// redirect("/display");
	// } catch (ExecutionException e) {
	// errorMessage(e);
	// }
	// }
}
