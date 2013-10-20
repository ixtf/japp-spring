package com.hengyi.japp.report.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Role;
import com.hengyi.japp.report.domain.repository.RoleRepository;
import com.hengyi.japp.report.service.RoleService;

@Named("roleService")
@Transactional
@SuppressWarnings("unchecked")
public class RoleServiceImpl extends AbstractCommonCrudNeo4jService<Role>
		implements RoleService {
	@Resource
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(roleRepository.findAllByQuery("name",
				nameSearch));
	}

	@Override
	public String getNewPath() {
		return "/admin/role";
	}

	@Override
	public <R extends Repository<Role, Long>> R getRepository() {
		return (R) roleRepository;
	}
}
