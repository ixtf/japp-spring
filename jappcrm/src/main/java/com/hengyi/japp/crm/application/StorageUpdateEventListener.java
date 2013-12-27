package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.event.StorageUpdateEvent;
import com.hengyi.japp.crm.service.storage.StorageService;

@Named
@Transactional
public class StorageUpdateEventListener implements
		ApplicationListener<StorageUpdateEvent> {
	@Inject
	private StorageService storageService;
	@Inject
	private Neo4jTemplate template;

	@Override
	public void onApplicationEvent(StorageUpdateEvent event) {
		Long nodeId = (Long) event.getSource();
		Storage storage = storageService.findOne(nodeId);
		Iterable<Indicator> indicators = storageService.findAllIndicator();
		for (Indicator indicator : indicators)
			storage.indicatorScore(template, indicator);
	}
}
