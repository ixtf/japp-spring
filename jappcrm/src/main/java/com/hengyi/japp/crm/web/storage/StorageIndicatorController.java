package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.web.IndicatorController;

@Named
@Scope("view")
public class StorageIndicatorController extends IndicatorController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;

	@Override
	protected Indicator newIndicator() {
		return new StorageIndicator();
	}
}
