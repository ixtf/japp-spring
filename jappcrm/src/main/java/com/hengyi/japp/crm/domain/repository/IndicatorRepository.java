package com.hengyi.japp.crm.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.IndicatorValueScore;

public interface IndicatorRepository extends GraphRepository<Indicator>,
		NamedIndexRepository<Indicator>,
		RelationshipOperationsRepository<Indicator> {
	@Query("start indicatorValue=node({0}) match indicatorValue<-[r:"
			+ IndicatorValueScore.RELATIONSHIP + "]-indicator return indicator")
	EndResult<Indicator> findAllByIndicatorValue(IndicatorValue indicatorValue);
}
