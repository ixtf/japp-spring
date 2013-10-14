package com.hengyi.japp.report.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.impl.CommonUrlServiceImpl;
import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Role;
import com.hengyi.japp.report.domain.repository.RoleRepository;
import com.hengyi.japp.report.service.RoleService;

@Named("roleService")
@Transactional
public class RoleServiceImpl extends CommonUrlServiceImpl<Long> implements
		RoleService {
	private static final long serialVersionUID = 5428467932953493451L;
	@Resource
	private RoleRepository roleRepository;

	@Override
	public Role findOne(Long nodeId) {
		return roleRepository.findOne(nodeId);
	}

	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void delete(Role role) {
		roleRepository.delete(role);
	}

	@Override
	public void delete(Long nodeId) {
		roleRepository.delete(nodeId);
	}

	@Override
	public List<Role> findAll(PageRequest pageRequest) {
		return Lists.newArrayList(roleRepository.findAll(pageRequest));
	}

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
	public long count() {
		return roleRepository.count();
	}
}
