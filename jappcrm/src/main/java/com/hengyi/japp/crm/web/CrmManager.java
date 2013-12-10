package com.hengyi.japp.crm.web;

import java.util.List;

import com.hengyi.japp.crm.data.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.dto.CrmDTO;

public abstract class CrmManager<T extends CrmDTO> extends AbstractController {
	protected T crm;
	protected List<Indicator> indicators;

	protected abstract CrmType getCrmType();
}
