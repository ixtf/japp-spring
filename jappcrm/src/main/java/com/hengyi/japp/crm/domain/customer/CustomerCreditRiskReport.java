package com.hengyi.japp.crm.domain.customer;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.Communicatee;

@NodeEntity
public interface CustomerCreditRiskReport {
	String getName();

	String getRegisterNumber();

	Date getRegisterDate();

	int getDurationYears();

	String getRegisterPlace();

	String getAddressName();

	String getZipCode();

	String getRepresent();

	BigDecimal getRegisterCapital();

	Communicatee getCommunicatee();
}
