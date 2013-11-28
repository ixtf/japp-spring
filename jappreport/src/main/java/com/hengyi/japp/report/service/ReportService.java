package com.hengyi.japp.report.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.ReportParam;

public interface ReportService<T extends Report> extends
		CommonCrudNeo4jService<T> {
	T newReport();

	void save(T report, Menu menu, Iterable<ReportParam> params)
			throws Exception;

	void delete(T report) throws Exception;

	List<T> findAllByQuery(String nameSearch) throws Exception;

	String getUrl(T report);

	List<Report> findAllByMenu(Menu menu);

	List<Report> findAllByMenu(Iterable<Menu> menus);

}
