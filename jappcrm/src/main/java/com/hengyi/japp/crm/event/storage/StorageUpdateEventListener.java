package com.hengyi.japp.crm.event.storage;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.service.StorageService;

@Named
@Transactional
public class StorageUpdateEventListener implements
		ApplicationListener<StorageUpdateEvent> {
	@Inject
	private StorageService storageService;

	@Override
	public void onApplicationEvent(StorageUpdateEvent event) {
		Long nodeId = (Long) event.getSource();
		Storage storage = storageService.findOne(nodeId);
		Iterable<Indicator> indicators = storageService.findAllIndicator();
		for (Indicator indicator : indicators)
			storage.indicatorScore(indicator);
	}
}
