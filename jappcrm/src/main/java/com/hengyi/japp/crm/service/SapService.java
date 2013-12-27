package com.hengyi.japp.crm.service;

import java.util.Date;
import java.util.List;

import com.hengyi.japp.common.service.CommonSapService;
import com.hengyi.japp.crm.web.model.EkkoModel;
import com.hengyi.japp.crm.web.model.VbakModel;

public interface SapService extends CommonSapService {
	List<VbakModel> findAllVbakModel(String kunnr, Date start, Date end)
			throws Exception;

	List<EkkoModel> findAllEkkoModel(String lifnr, Date start, Date end)
			throws Exception;

	String convertKunnr(String kunnr) throws Exception;

	String convertLifnr(String lifnr) throws Exception;
}
