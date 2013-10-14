package com.hengyi.japp.report.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.service.CommonUrlService;
import com.hengyi.japp.report.domain.Operator;

public interface OperatorService extends CommonUrlService<Long>, Serializable {
	Operator findOne(Long nodeId);

	Operator findOne(String uuid) throws Exception;

	Operator findOne(UserDTO user) throws Exception;

	void save(Operator operator) throws Exception;

	void updateTheme(String uuid, String theme) throws Exception;

	List<Operator> findAll(PageRequest pageRequest);

	long count();

	List<Operator> findAllByQuery(String nameSearch) throws Exception;
}
