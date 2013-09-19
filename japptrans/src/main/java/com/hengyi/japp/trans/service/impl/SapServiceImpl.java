package com.hengyi.japp.trans.service.impl;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.sap.Constant;
import com.hengyi.japp.common.sap.destination.DestinationType;
import com.hengyi.japp.common.service.impl.CommonSapServiceImpl;
import com.hengyi.japp.trans.domain.PackType;
import com.hengyi.japp.trans.domain.TransType;
import com.hengyi.japp.trans.domain.ys.YsPackType;
import com.hengyi.japp.trans.domain.ys.YsTransType;
import com.hengyi.japp.trans.service.SapService;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

@Named
@Singleton
public class SapServiceImpl extends CommonSapServiceImpl implements SapService {
	private static final String DOMNAME_PACKTYPE = "ZYS_PACK_TYPE";
	private static final String DOMNAME_TRANSTYPE = "ZYS_TRANS_TYPE";

	@Override
	public JCoTable findAllDomvalue(String I_DOMNAME) throws Exception {
		JCoFunction function = getFunction(DestinationType.DEV,
				Constant.FunctionName.ZFINDALL_DD_DOMVALUE);
		function.getImportParameterList().setValue("I_DOMNAME", I_DOMNAME);
		execute(DestinationType.DEV, function);
		return function.getTableParameterList().getTable("ET_DD07V");
	}

	@Override
	public Iterable<PackType> findAllPackType() throws Exception {
		List<PackType> result = Lists.newArrayList();
		JCoTable table = findAllDomvalue(DOMNAME_PACKTYPE);
		if (table.isEmpty())
			return result;
		do {
			PackType packType = new YsPackType();
			packType.setId(table.getInt("DOMVALUE_L"));
			packType.setName(table.getString("DDTEXT"));
			result.add(packType);
		} while (table.nextRow());
		return result;
	}

	@Override
	public Iterable<TransType> findAllTransType() throws Exception {
		List<TransType> result = Lists.newArrayList();
		JCoTable table = findAllDomvalue(DOMNAME_TRANSTYPE);
		if (table.isEmpty())
			return result;
		do {
			TransType transType = new YsTransType();
			transType.setId(table.getInt("DOMVALUE_L"));
			transType.setName(table.getString("DDTEXT"));
			result.add(transType);
		} while (table.nextRow());
		return result;
	}
}
