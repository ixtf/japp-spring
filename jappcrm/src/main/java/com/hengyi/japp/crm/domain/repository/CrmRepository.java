package com.hengyi.japp.crm.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorScore;

public interface CrmRepository extends GraphRepository<Crm>,
		NamedIndexRepository<Crm>, RelationshipOperationsRepository<Crm> {
	@Query("start indicator=node({0}) match indicator<-[r:"
			+ IndicatorScore.RELATIONSHIP + "]-crm  return crm")
	EndResult<Crm> findAllByIndicator(Indicator indicator);
}
