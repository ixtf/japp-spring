package com.hengyi.japp.crm.domain.node.storage;

import java.io.Serializable;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.node.AbstractCrm;

@NodeEntity
public class Storage extends AbstractCrm implements Serializable {
	private static final long serialVersionUID = -831678558917732185L;
}
