package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;
import java.util.Set;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Sets;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.web.CrmController;

@Named
@Scope("view")
public class StorageController extends CrmController implements Serializable {
	private static final long serialVersionUID = -4189240961754260470L;

	@Override
	protected Crm newCrm() {
		return new Storage();
	}

	@Override
	protected Iterable<Indicator> getAssociatedIndicators() {
		Set<Indicator> result = Sets.newHashSet();
		for (Indicator indicator : storageService.findAllIndicator())
			result.add(indicator);
		return result;
	}
}
