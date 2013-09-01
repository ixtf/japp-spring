package com.hengyi.japp.crm.domain.storage;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.Crm;

@NodeEntity
public class Storage extends Crm implements StorageCreditRatingReport {
	private static final long serialVersionUID = -831678558917732185L;
}
