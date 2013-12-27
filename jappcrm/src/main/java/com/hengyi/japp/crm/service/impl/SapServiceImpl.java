package com.hengyi.japp.crm.service.impl;

import static com.hengyi.japp.crm.Constant.SapFunction.CONVERT_KUNNR;
import static com.hengyi.japp.crm.Constant.SapFunction.CONVERT_LIFNR;
import static com.hengyi.japp.crm.Constant.SapFunction.F_EKKO;
import static com.hengyi.japp.crm.Constant.SapFunction.F_VBAK;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.sap.CommonSapUtil;
import com.hengyi.japp.common.sap.dto.SapVbakDTO;
import com.hengyi.japp.common.sap.dto.SapVbapDTO;
import com.hengyi.japp.common.sap.dto.SapVbkdDTO;
import com.hengyi.japp.common.service.AbstractCommonSapService;
import com.hengyi.japp.crm.MyUtil;
import com.hengyi.japp.crm.service.SapService;
import com.hengyi.japp.crm.web.model.EkkoModel;
import com.hengyi.japp.crm.web.model.VbakModel;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

@Named
@Transactional
public class SapServiceImpl extends AbstractCommonSapService implements
		SapService {

	public SapServiceImpl() throws Exception {
		super();
	}

	@Override
	public List<EkkoModel> findAllEkkoModel(String lifnr, Date start, Date end)
			throws Exception {
		// TODO Auto-generated method stub
		List<EkkoModel> result = Lists.newArrayList();
		JCoFunction f = getFunction(F_EKKO);
		f.getImportParameterList().setValue("I_LIFNR", lifnr);
		f.getImportParameterList().setValue("I_SDATE", start);
		f.getImportParameterList().setValue("I_EDATE", end);
		execute(f);
		return result;
	}

	@Override
	public List<VbakModel> findAllVbakModel(String kunnr, Date start, Date end)
			throws Exception {
		List<VbakModel> result = Lists.newArrayList();
		JCoFunction f = getFunction(F_VBAK);
		f.getImportParameterList().setValue("I_KUNNR", kunnr);
		f.getImportParameterList().setValue("I_SDATE", start);
		f.getImportParameterList().setValue("I_EDATE", end);
		execute(f);
		JCoTable table = f.getExportParameterList().getTable("EST_VBAK");
		List<SapVbakDTO> sapVbaks = CommonSapUtil.convert(table,
				SapVbakDTO.class);
		table = f.getExportParameterList().getTable("EST_VBKD");
		Map<String, SapVbkdDTO> sapVbkdMap = MyUtil.map(
				CommonSapUtil.convert(table, SapVbkdDTO.class), "vbeln");
		table = f.getExportParameterList().getTable("EST_VBAP");
		Map<String, List<SapVbapDTO>> sapVbapMap = MyUtil.listMap(
				CommonSapUtil.convert(table, SapVbapDTO.class), "vbeln");

		for (SapVbakDTO sapVbak : sapVbaks) {
			VbakModel model = new VbakModel();
			model.setSapVbak(sapVbak);
			String vbeln = sapVbak.getVbeln();
			model.setSapVbkd(sapVbkdMap.get(vbeln));
			model.setSapVbap(sapVbapMap.get(vbeln));
			result.add(model);
		}
		return result;
	}

	@Override
	public String convertKunnr(String kunnr) throws Exception {
		JCoFunction f = getFunction(CONVERT_KUNNR);
		f.getImportParameterList().setValue("I_KUNNR", kunnr);
		execute(f);
		return f.getExportParameterList().getString("E_KUNNR");
	}

	@Override
	public String convertLifnr(String lifnr) throws Exception {
		JCoFunction f = getFunction(CONVERT_LIFNR);
		f.getImportParameterList().setValue("I_LIFNR", lifnr);
		execute(f);
		return f.getExportParameterList().getString("E_LIFNR");
	}

}
