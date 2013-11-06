package com.hengyi.japp.report.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.repository.MenuRepository;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.exception.ParentMenuIsSelfException;
import com.hengyi.japp.report.service.MenuService;

@Named("menuService")
@Transactional
@SuppressWarnings("unchecked")
public class MenuServiceImpl extends AbstractCommonCrudNeo4jService<Menu>
		implements MenuService {
	@Resource
	protected Neo4jOperations template;
	@Resource
	private MenuRepository menuRepository;
	@Resource
	private ReportRepository reportRepository;

	@Override
	public void save(Menu menu, Menu parent) throws Exception {
		if (menu.equals(parent))
			throw new ParentMenuIsSelfException();
		menu.setParent(parent);
		save(menu);
	}

	@Override
	public List<Menu> findAllTopMenu() {
		List<Menu> result = Lists.newArrayList();
		for (Menu menu : findAll())
			if (menu.getParent() == null)
				result.add(menu);
		return result;
	}

	@Override
	public List<Report> findAllReport(Menu menu) {
		return Lists.newArrayList(reportRepository.findAllByMenu(menu));
	}

	@Override
	public List<Report> findAllReport(Iterable<Menu> menus) {
		return Lists.newArrayList(reportRepository.findAllByMenu(menus));
	}

	@Override
	public List<Menu> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(menuRepository.findAllByQuery(
				Menu.class.getSimpleName(), "name", nameSearch));
	}

	@Override
	public String getNewPath() {
		return "/admin/menu";
	}

	@Override
	public <R extends Repository<Menu, Long>> R getRepository() {
		return (R) menuRepository;
	}
}
