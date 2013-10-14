package com.hengyi.japp.report.ws;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jws.WebService;

import org.dozer.Mapper;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.hengyi.japp.report.service.CacheService;
import com.hengyi.japp.report.service.OperatorService;
import com.hengyi.japp.report.service.RoleService;

@WebService
public class SoapServiceImpl implements SoapService {
	@Resource
	protected Neo4jOperations template;
	@Resource
	protected Mapper dozer;
	@Inject
	protected CacheService cacheService;
	@Inject
	protected OperatorService operatorService;
	@Inject
	protected RoleService roleService;

	@Override
	public void test() {
		// TODO Auto-generated method stub

	}
}
