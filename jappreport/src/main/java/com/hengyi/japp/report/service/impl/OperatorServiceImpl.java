package com.hengyi.japp.report.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.event.EventPublisher;
import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.Role;
import com.hengyi.japp.report.domain.repository.OperatorRepository;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.domain.repository.RoleRepository;
import com.hengyi.japp.report.service.MenuService;
import com.hengyi.japp.report.service.OperatorService;
import com.hengyi.japp.report.web.model.FavoriteReportModel;
import com.hengyi.japp.report.web.model.TopMenuModel;

@Named("operatorService")
@Transactional
@SuppressWarnings("unchecked")
public class OperatorServiceImpl extends
		AbstractCommonCrudNeo4jService<Operator> implements OperatorService {
	@Resource
	protected Neo4jOperations template;
	@Resource
	private OperatorRepository operatorRepository;
	@Resource
	private ReportRepository reportRepository;
	@Resource
	private RoleRepository roleRepository;
	@Resource
	private MenuService menuService;
	@Inject
	private EventPublisher eventPublisher;

	@Override
	public void save(Operator operator, Iterable<Role> roles,
			Iterable<Menu> menus, Iterable<? extends Report> reports)
			throws Exception {
		operator.setRoles(roles);
		operator.setMenus(menus);
		operator.setReports(reports);
		save(operator);
	}

	@Override
	public List<TopMenuModel> findTopMenu(Operator operator) {
		return MyUtil.isSuperUser() ? findAllTopMenu(reportRepository.findAll())
				: findAllTopMenu(MyUtil.getAllReport(operator, template,
						menuService));
	}

	private List<TopMenuModel> findAllTopMenu(Iterable<Report> reports) {
		List<TopMenuModel> result = Lists.newArrayList();
		Multimap<Menu, Report> map = ArrayListMultimap.create();
		for (Report report : reports)
			map.put(getTopMenu(report.getMenu()), report);
		for (Menu menu : map.keySet())
			result.add(new TopMenuModel(menu, map.get(menu)));
		return result;
	}

	private Menu getTopMenu(Menu menu) {
		Menu parent = menu.getParent();
		if (parent == null)
			return menu;
		else
			return getTopMenu(parent);
	}

	@Override
	public List<FavoriteReportModel> findAllFavorites(Operator operator) {
		List<FavoriteReportModel> result = Lists.newArrayList();
		operator = findOne(operator.getNodeId());
		Multimap<Menu, Report> map = ArrayListMultimap.create();
		for (Report report : operator.getFavoriteReports(template))
			map.put(getTopMenu(report.getMenu()), report);
		for (Menu menu : map.keySet())
			result.add(new FavoriteReportModel(menu, map.get(menu)));
		return result;
	}

	@Override
	public void favorite(Operator operator, Report report) throws Exception {
		// 把用户重新取出,避免保存的时候丢失其他信息
		operator = findOne(operator.getNodeId());
		Set<Report> reports = Sets.newHashSet(operator.getFavoriteReports());
		if (!reports.add(report))
			return;
		operator.setFavoriteReports(reports);
		save(operator);
	}

	@Override
	public void unFavorite(Operator operator, Report report) throws Exception {
		operator = findOne(operator.getNodeId());
		Set<Report> reports = Sets.newHashSet(operator.getFavoriteReports());
		if (!reports.remove(report))
			return;
		operator.setFavoriteReports(reports);
		save(operator);
	}

	@Override
	public Operator findOne(String uuid) {
		return uuid == null ? null : operatorRepository.findByPropertyValue(
				Operator.class.getSimpleName(), "uuid", uuid);
	}

	@Override
	public Operator findOne(UserDTO user) throws Exception {
		Operator operator = findOne(user.getUuid());
		if (operator == null) {
			operator = new Operator(user.getUuid(), user.getName());
			save(operator);
		}
		return operator;
	}

	@Override
	public void updateTheme(String uuid, String theme) throws Exception {
		Operator operator = findOne(uuid);
		operator.setTheme(theme);
		save(operator);
	}

	@Override
	public String getNewPath() {
		return "/admin/operator";
	}

	@Override
	public String getNewView() {
		return getViewPrefix() + "/new.jsf";
	}

	@Override
	public List<Operator> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(operatorRepository.findAllByQuery(
				Operator.class.getSimpleName(), "name", nameSearch));
	}

	@Override
	public <R extends Repository<Operator, Long>> R getRepository() {
		return (R) operatorRepository;
	}
}
