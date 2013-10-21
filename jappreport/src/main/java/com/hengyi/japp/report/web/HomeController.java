package com.hengyi.japp.report.web;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.model.MenuModel;
import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.event.operator.CollectEvent;
import com.hengyi.japp.report.service.ReportService;

@Named
@Scope("session")
public class HomeController extends AbstractController implements Serializable {
	private static final long serialVersionUID = -2095926297894443335L;
	// 收藏的报表
	private List<Report> reports = Lists.newArrayList();

	public List<Report> getReports() {
		return reports;
	}

	public List<Report> completeReport(String query) throws Exception {
		Set<Report> result = Sets.newHashSet();
		query = "*" + query + "*";
		for (ReportService<?> reportService : reportFactory.reportService())
			result.addAll(reportService.findAllByQuery(query));
		return Lists.newArrayList(result);
	}

	public void collectMenuAction(ActionEvent event) {
		Report report = (Report) event.getComponent().getAttributes()
				.get("report");
		if (reports.contains(report)) {
			operationSuccessMessage();
			return;
		}
		try {
			Operator operator = getCurrentOperator();
			operatorService.collect(operator, report);
			if (!reports.contains(report))
				reports.add(report);
			CollectEvent collectEvent = new CollectEvent(this,
					operator.getNodeId(), report.getNodeId());
			eventPublisher.publish(collectEvent);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	// @Subscribe
	public void updateFavoriteReport(CollectEvent event) {
		Report report = reportRepository.findOne(event.getReportNodeId());
		if (!reports.contains(report))
			reports.add(report);
	}

	@PostConstruct
	public void init() throws Exception {
		eventBus.register(this);
		menuBar = cacheService.getMenuBar();
		reports = Lists.newArrayList(getCurrentOperator().getFavoriteReports(
				template));
	}

	@PreDestroy
	public void destroy() {
		eventBus.unregister(this);
	}

	private MenuModel menuBar;

	public MenuModel getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuModel menuBar) {
		this.menuBar = menuBar;
	}
}
