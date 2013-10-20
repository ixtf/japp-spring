package com.hengyi.japp.crm.domain.storage;

import java.io.Serializable;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.report.Report;

@NodeEntity
public class StorageReport extends Report implements Serializable {
	private static final long serialVersionUID = 3994195669260400485L;
}
