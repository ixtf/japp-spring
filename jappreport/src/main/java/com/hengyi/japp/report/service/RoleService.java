package com.hengyi.japp.report.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.Role;

public interface RoleService extends CommonCrudNeo4jService<Role> {
	void save(Role role, Iterable<Menu> menus, Iterable<Report> reports)
			throws Exception;

	List<Role> findAllByQuery(String nameSearch) throws Exception;

	List<Operator> findAllOperator(Role role);
}