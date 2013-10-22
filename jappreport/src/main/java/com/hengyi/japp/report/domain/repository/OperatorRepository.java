package com.hengyi.japp.report.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Role;

public interface OperatorRepository extends GraphRepository<Operator>,
		NamedIndexRepository<Operator>,
		RelationshipOperationsRepository<Operator> {
	@Query(value = "START n=node({0}) MATCH n<-[:" + Operator.OPERATOR_ROLE
			+ "]-operator RETURN operator")
	EndResult<Operator> findAllByRole(Role role);
}
