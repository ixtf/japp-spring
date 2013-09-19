package com.hengyi.japp.trans.service.impl;

import static com.hengyi.japp.common.CommonConstant.ADMIN_NAME;
import static com.hengyi.japp.common.CommonConstant.ADMIN_PRINCIPAL;
import static com.hengyi.japp.common.CommonConstant.SESSION_OPERATOR;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.service.impl.CommonCacheServiceImpl;
import com.hengyi.japp.trans.domain.Operator;
import com.hengyi.japp.trans.domain.repository.PackTypeRepository;
import com.hengyi.japp.trans.domain.repository.TransTypeRepository;
import com.hengyi.japp.trans.service.CacheService;
import com.hengyi.japp.trans.service.OperatorService;
import com.hengyi.japp.trans.service.SapService;

@Named("cacheService")
@Singleton
public class CacheServiceImpl extends CommonCacheServiceImpl implements
		CacheService {
	@Inject
	private OperatorService operatorService;
	@Inject
	private SapService sapService;
	@Inject
	private PackTypeRepository packTypeRepository;
	@Inject
	private TransTypeRepository transTypeRepository;

	@Override
	public Operator getCurrentOperator() throws Exception {
		Operator operator = getSession(SESSION_OPERATOR);
		if (operator != null)
			return operator;
		if (!isAuthenticated())
			return operator;
		if (ADMIN_PRINCIPAL.equals(getPrincipal()))
			operator = new Operator(ADMIN_PRINCIPAL, ADMIN_NAME);
		else
			operator = operatorService.findOne(getUser());
		setSession(SESSION_OPERATOR, operator);
		return operator;
	}

	@PostConstruct
	@Transactional
	private void initPackType() throws Exception {
		packTypeRepository.save(sapService.findAllPackType());
	}

	@PostConstruct
	@Transactional
	private void initTransType() throws Exception {
		transTypeRepository.save(sapService.findAllTransType());
	}
}
