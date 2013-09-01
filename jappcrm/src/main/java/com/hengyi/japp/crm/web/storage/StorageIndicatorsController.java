package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.web.AbstractController;

@Named
@Scope("view")
public class StorageIndicatorsController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private List<StorageIndicator> indicators;
	private StorageIndicator indicator;

	public List<StorageIndicator> getIndicators() {
		if (indicators == null)
			indicators = storageService.findAllIndicator();
		return indicators;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(StorageIndicator indicator) {
		this.indicator = indicator;
	}
}
