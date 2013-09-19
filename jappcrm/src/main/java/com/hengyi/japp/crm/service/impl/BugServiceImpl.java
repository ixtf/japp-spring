package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Bug;
import com.hengyi.japp.crm.domain.repository.BugRepository;
import com.hengyi.japp.crm.service.BugService;

@Named
@Transactional
public class BugServiceImpl implements BugService {
	@Inject
	private BugRepository bugRepository;

	@Override
	public Bug findOne(Long nodeId) {
		return bugRepository.findOne(nodeId);
	}

	@Override
	public void save(Bug bug) {
		bugRepository.save(bug);
	}

	@Override
	public void delete(Bug bug) {
		bugRepository.delete(bug);
	}

	@Override
	public List<Bug> findAll(PageRequest pageRequest) {
		return Lists.newArrayList(bugRepository.findAll(pageRequest));
	}

	@Override
	public long count() {
		return bugRepository.count();
	}

	@Override
	public List<Bug> findAllByQuery(String nameSearch) {
		nameSearch = StringUtils.trimToEmpty(nameSearch);

		return Lists.newArrayList(bugRepository.findAllByQuery("name",
				nameSearch));
	}
}
