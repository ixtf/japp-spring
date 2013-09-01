package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;

public interface IndicatorService<T extends Indicator> {
	T findOneIndicator(Long nodeId);

	T findOneIndicator(String name);

	void save(T indicator, Iterable<IndicatorValueScore> indicatorValueScores)
			throws Exception;

	void delete(T indicator) throws Exception;

	List<T> findAllIndicator();

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
