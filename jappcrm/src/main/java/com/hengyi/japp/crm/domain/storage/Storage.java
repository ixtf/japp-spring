package com.hengyi.japp.crm.domain.storage;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.Crm;

@NodeEntity
public class Storage extends Crm {
	private static final long serialVersionUID = -831678558917732185L;
	// public static final String FIELD_CAPACITY = "capacity";
	@NotNull
	@Min(0)
	private BigDecimal capacity;

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}
}
