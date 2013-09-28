package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.service.IndicatorService;
import com.hengyi.japp.crm.service.storage.StorageIndicatorService;
import com.hengyi.japp.crm.web.IndicatorController;

@Named
@Scope("view")
public class StorageIndicatorController extends
		IndicatorController<StorageIndicator> implements Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	@Inject
	private StorageIndicatorService storageIndicatorService;

	@Override
	protected IndicatorService<StorageIndicator> getIndicatorService() {
		return storageIndicatorService;
	}
}
