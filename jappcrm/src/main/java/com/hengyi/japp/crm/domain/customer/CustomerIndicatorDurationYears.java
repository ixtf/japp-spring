package com.hengyi.japp.crm.domain.customer;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.hengyi.japp.crm.data.IndicatorType;
import com.hengyi.japp.crm.domain.Crm;

@NodeEntity
public class CustomerIndicatorDurationYears extends CustomerIndicator {
	private static final long serialVersionUID = 2420361128761811827L;
	private static Logger log = LoggerFactory
			.getLogger(CustomerIndicatorDurationYears.class);
	@NotBlank
	private String field;

	public CustomerIndicatorDurationYears() {
		super("已经营年限", 0.06);
		setIndicatorType(IndicatorType.CALCULATE);
		setField(Crm.FIELD_DURATIONYEARS);
	}

	@Override
	protected double calculateScoreByCalculate(Crm crm, Neo4jOperations template)
			throws Exception {
		int durationYears;
		try {
			durationYears = (int) PropertyUtils.getProperty(crm, getField());
		} catch (Exception e) {
			log.error(crm + "", e);
			throw e;
		}
		if (durationYears >= 10)
			return 10;
		else if (durationYears >= 5)
			return 9;
		else if (durationYears >= 3)
			return 8;
		else if (durationYears >= 1)
			return 7;
		else
			return 6;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		// TODO 把中间的空格也去掉
		this.field = StringUtils.trim(field);
	}
}
