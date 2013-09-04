package com.hengyi.japp.crm.domain.customer;

import java.math.BigDecimal;

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
public class CustomerIndicatorRegisterCapcital extends CustomerIndicator {
	private static final long serialVersionUID = 2420361128761811827L;
	private static Logger log = LoggerFactory
			.getLogger(CustomerIndicatorRegisterCapcital.class);
	@NotBlank
	private String field;

	public CustomerIndicatorRegisterCapcital() {
		super("注册资本", 0.12);
		setIndicatorType(IndicatorType.CALCULATE);
		setField(Crm.FIELD_REGISTERCAPITAL);
	}

	@Override
	protected double calculateScoreByCalculate(Crm crm, Neo4jOperations template)
			throws Exception {
		// 单位：亿
		double registerCapital;
		try {
			BigDecimal b = (BigDecimal) PropertyUtils.getProperty(crm,
					getField());
			registerCapital = b.doubleValue() / 100000000;
		} catch (Exception e) {
			log.error(crm + "", e);
			throw e;
		}
		if (registerCapital >= 5)
			return 10;
		else if (registerCapital >= 3)
			return 9;
		else if (registerCapital >= 2)
			return 8;
		else if (registerCapital >= 1)
			return 7;
		else if (registerCapital >= 0.5)
			return 6;
		else if (registerCapital >= 0.3)
			return 5;
		else if (registerCapital >= 0.2)
			return 4;
		else if (registerCapital >= 0.1)
			return 3;
		else if (registerCapital >= 0.05)
			return 2;
		else
			return 1;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		// TODO 把中间的空格也去掉
		this.field = StringUtils.trim(field);
	}
}
