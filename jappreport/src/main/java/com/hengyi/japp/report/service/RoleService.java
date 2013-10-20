package com.hengyi.japp.report.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.report.domain.Role;

public interface RoleService extends CommonCrudNeo4jService<Role> {
	List<Role> findAllByQuery(String nameSearch) throws Exception;
}
