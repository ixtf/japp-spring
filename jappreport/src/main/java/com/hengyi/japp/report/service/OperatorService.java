package com.hengyi.japp.report.service;

import java.util.List;

import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.Role;

public interface OperatorService extends CommonCrudNeo4jService<Operator> {
	Operator findOne(String uuid) throws Exception;

	Operator findOne(UserDTO user) throws Exception;

	void save(Operator operator, Iterable<Role> roles, Iterable<Menu> menus,
			Iterable<? extends Report> reports) throws Exception;

	void updateTheme(String uuid, String theme) throws Exception;

	List<Operator> findAllByQuery(String nameSearch) throws Exception;

	void collect(Operator operator, Report report) throws Exception;
}
