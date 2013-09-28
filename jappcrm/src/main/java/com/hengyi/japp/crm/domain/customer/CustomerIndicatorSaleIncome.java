package com.hengyi.japp.crm.domain.customer;

import java.math.BigDecimal;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.data.IndicatorType;
import com.hengyi.japp.crm.domain.Crm;

@NodeEntity
public class CustomerIndicatorSaleIncome extends CustomerIndicator {
	private static final long serialVersionUID = 2420361128761811827L;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@NotBlank
	private String field;

	public CustomerIndicatorSaleIncome() {
		super("销售收入", 0.12);
		setIndicatorType(IndicatorType.CALCULATE);
		setField(Crm.FIELD_SALEINCOME);
	}

	@Override
	public double calculateScore(Crm crm) {
		// 单位：亿
		double saleIncome;
		try {
			BigDecimal b = (BigDecimal) PropertyUtils.getProperty(crm,
					getField());
			saleIncome = b.doubleValue() / 100000000;
		} catch (Exception e) {
			log.error(crm + "", e);
			return 0;
		}
		if (saleIncome >= 1000)
			return 10;
		else if (saleIncome >= 500)
			return 9;
		else if (saleIncome >= 300)
			return 8;
		else if (saleIncome >= 100)
			return 7;
		else if (saleIncome >= 50)
			return 6;
		else if (saleIncome >= 10)
			return 5;
		else if (saleIncome >= 1)
			return 4;
		else if (saleIncome >= 0.5)
			return 3;
		else if (saleIncome >= 0.1)
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
