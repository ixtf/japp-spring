package com.hengyi.japp.crm.domain.customer;

import java.io.Serializable;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.Report;

@NodeEntity
public class CustomerReport extends Report implements Serializable {
	private static final long serialVersionUID = 3108621077521455426L;

}
