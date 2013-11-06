package com.hengyi.japp.report.web;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.ReportService;
import com.hengyi.japp.report.web.model.FavoriteReportModel;
import com.hengyi.japp.report.web.model.TopMenuModel;

@Named
@Scope("session")
public class HomeController extends AbstractController implements Serializable {
	private static final long serialVersionUID = -2095926297894443335L;
	private Operator operator;
	// 菜单栏的报表
	private List<TopMenuModel> topMenus;
	// 收藏的报表
	private List<FavoriteReportModel> favorites;
	private Report favorite;

	@PostConstruct
	private void init() {
		eventBus.register(this);
		try {
			operator = getCurrentOperator();
			topMenus = operatorService.findTopMenu(operator);
			favorites = operatorService.findAllFavorites(operator);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	@PreDestroy
	public void destroy() {
		eventBus.unregister(this);
	}

	public List<Report> completeReport(String query) throws Exception {
		Set<Report> result = Sets.newHashSet();
		query = "*" + query + "*";
		for (ReportService<?> reportService : reportFactory.reportService())
			result.addAll(reportService.findAllByQuery(query));
		return Lists.newArrayList(result);
	}

	public void favorite() {
		try {
			operatorService.favorite(operator, favorite);
			favorites = operatorService.findAllFavorites(operator);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void unFavorite() {
		try {
			operatorService.unFavorite(operator, favorite);
			favorites = operatorService.findAllFavorites(operator);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public List<TopMenuModel> getTopMenus() {
		return topMenus;
	}

	public List<FavoriteReportModel> getFavorites() {
		return favorites;
	}

	public Report getFavorite() {
		return favorite;
	}

	public void setFavorite(Report favorite) {
		this.favorite = favorite;
	}
}
