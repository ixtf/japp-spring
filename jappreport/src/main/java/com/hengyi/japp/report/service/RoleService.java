package com.hengyi.japp.report.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.common.service.CommonUrlService;
import com.hengyi.japp.report.domain.Role;

public interface RoleService extends CommonUrlService<Long>, Serializable {
	Role findOne(Long nodeId);

	void save(Role role);

	void delete(Role role);

	void delete(Long nodeId);

	List<Role> findAll(PageRequest pageRequest);

	List<Role> findAllByQuery(String nameSearch) throws Exception;

	long count();
}
