package com.hengyi.japp.report.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.service.impl.CommonUrlServiceImpl;
import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.repository.OperatorRepository;
import com.hengyi.japp.report.service.OperatorService;

@Named("operatorService")
@Transactional
public class OperatorServiceImpl extends CommonUrlServiceImpl<Long> implements
		OperatorService {
	private static final long serialVersionUID = -8878721144489697640L;
	@Resource
	private OperatorRepository operatorRepository;

	@Override
	public Operator findOne(Long nodeId) {
		return nodeId == null ? null : operatorRepository.findOne(nodeId);
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
	public void save(Operator operator) throws Exception {
		operatorRepository.save(operator);
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
	public List<Operator> findAll(PageRequest pageRequest) {
		return Lists.newArrayList(operatorRepository.findAll(pageRequest));
	}

	@Override
	public long count() {
		return operatorRepository.count();
	}

	@Override
	public List<Operator> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(operatorRepository.findAllByQuery("name",
				nameSearch));
	}
}
