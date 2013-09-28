package com.hengyi.japp.crm.domain.storage;

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
public class StorageIndicatorCapcital extends StorageIndicator {
	private static final long serialVersionUID = 2420361128761811827L;
	private static Logger log = LoggerFactory
			.getLogger(StorageIndicatorCapcital.class);
	@NotBlank
	private String field;

	public StorageIndicatorCapcital() {
		super("仓储容量", 0.08);
		setIndicatorType(IndicatorType.CALCULATE);
		setField(Storage.FIELD_CAPACITY);
	}

	@Override
	public double calculateScore(Crm crm) {
		// 单位：万吨
		double capital;
		try {
			BigDecimal b = (BigDecimal) PropertyUtils.getProperty(crm,
					getField());
			capital = b.doubleValue() / 10000;
		} catch (Exception e) {
			log.error(crm + "", e);
			return 0;
		}
		if (capital >= 100)
			return 10;
		else if (capital >= 70)
			return 9;
		else if (capital >= 50)
			return 8;
		else if (capital >= 30)
			return 7;
		else if (capital >= 10)
			return 6;
		else if (capital >= 5)
			return 5;
		else
			return 4;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		// TODO 把中间的空格也去掉
		this.field = StringUtils.trim(field);
	}
}
