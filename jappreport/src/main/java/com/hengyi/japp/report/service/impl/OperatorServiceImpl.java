package com.hengyi.japp.report.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
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
import com.hengyi.japp.report.domain.repository.RoleRepository;
import com.hengyi.japp.report.service.OperatorService;

@Named("operatorService")
@Transactional
@SuppressWarnings("unchecked")
public class OperatorServiceImpl extends
		AbstractCommonCrudNeo4jService<Operator> implements OperatorService {
	@Resource
	private OperatorRepository operatorRepository;
	@Resource
	private RoleRepository roleRepository;
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
	public void collect(Operator operator, Report report) throws Exception {
		// 把用户重新取出,避免保存的时候丢失其他信息
		operator = findOne(operator.getNodeId());
		Set<Report> reports = Sets.newHashSet(operator.getFavoriteReports());
		if (!reports.add(report))
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
