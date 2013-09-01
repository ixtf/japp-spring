package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;

public interface IndicatorService {
	Indicator findOne(Long nodeId);

	void save(Indicator indicator,
			Iterable<IndicatorValueScore> indicatorValueScores)
			throws Exception;

	void delete(Indicator indicator) throws Exception;

	List<Indicator> findAll();

	// T findSaleIncomeIndicator();
	//
	// T findRegisterCapitalIndicator();
	//
	// T findCountryIndicator();
	//
	// T findDurationYearsIndicator();
	//
	// T findCrmPropertyIndicator();
	//
	// T findQualityCertificationIndicator();
	//
	// T findBrandIndicator();
	//
	// T findRegisterCapitalIndicator();
	//
	// T findRegisterCapitalIndicator();
	//
	// T findRegisterCapitalIndicator();
	//
	// T findRegisterCapitalIndicator();
}
