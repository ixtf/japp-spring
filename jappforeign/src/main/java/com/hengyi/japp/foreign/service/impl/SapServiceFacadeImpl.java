package com.hengyi.japp.foreign.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.hengyi.japp.common.sap.CommonSapUtil;
import com.hengyi.japp.common.sap.annotation.FunctionHandler;
import com.hengyi.japp.common.sap.dto.SapKna1DTO;
import com.hengyi.japp.common.sap.dto.SapLikpDTO;
import com.hengyi.japp.common.sap.dto.SapLipsDTO;
import com.hengyi.japp.common.sap.dto.SapTvzbtDTO;
import com.hengyi.japp.common.sap.dto.SapVbakDTO;
import com.hengyi.japp.common.sap.dto.SapVbapDTO;
import com.hengyi.japp.common.sap.dto.SapVbkdDTO;
import com.hengyi.japp.common.sap.dto.SapVbkdvbDTO;
import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.common.service.impl.CommonSapServiceImpl;
import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.dto.ForeignSapLikpDTO;
import com.hengyi.japp.foreign.dto.ForeignSapVbakDTO;
import com.hengyi.japp.foreign.service.SapServiceFacade;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

@Named
@Singleton
public class SapServiceFacadeImpl extends CommonSapServiceImpl implements
		SapServiceFacade {
	private Cache<String, ForeignSapVbakDTO> vbakCache = CacheBuilder
			.newBuilder().maximumSize(100).build();
	private Cache<String, ForeignSapLikpDTO> likpCache = CacheBuilder
			.newBuilder().maximumSize(100).build();

	@Inject
	private ApplicationContext context;

	@Override
	public ForeignSapVbakDTO findVbak(String vbeln) throws Exception {
		String key = StringUtils.trim(vbeln).toUpperCase();
		ForeignSapVbakDTO vbak = vbakCache.getIfPresent(key);
		return vbak != null ? vbak : getVbakFromSap(vbeln);
	}

	@Override
	public ForeignSapLikpDTO findLikp(String vbeln) throws Exception {
		String key = StringUtils.trim(vbeln).toUpperCase();
		ForeignSapLikpDTO likp = likpCache.getIfPresent(key);
		return likp != null ? likp : getLikpFromSap(vbeln);
	}

	private ForeignSapVbakDTO getVbakFromSap(String vbeln) throws Exception {
		JCoFunction function = getFunction(Constant.SapFunction.FIND_VBAK);
		JCoParameterList list = function.getImportParameterList();
		list.setValue("I_VBELN", vbeln);
		execute(function);

		list = function.getExportParameterList();
		SapVbakDTO sapVbak = CommonSapUtil.convert(list.getStructure("E_VBAK"),
				SapVbakDTO.class);
		SapVbkdvbDTO sapVbkdvb = CommonSapUtil.convert(list.getStructure("E_VBKD"),
				SapVbkdvbDTO.class);
		SapKna1DTO sapKna1 = CommonSapUtil.convert(list.getStructure("E_KNA1"),
				SapKna1DTO.class);
		SapTvzbtDTO sapTvzbt = CommonSapUtil.convert(list.getStructure("E_TVZBT"),
				SapTvzbtDTO.class);
		List<SapVbapDTO> sapVbaps = CommonSapUtil.convert(list.getTable("EST_VBAP"),
				SapVbapDTO.class);
		String key1 = StringUtils.trim(list.getString("E_VBELN_1"));
		String key2 = StringUtils.trim(list.getString("E_VBELN_2"));

		ForeignSapVbakDTO vbak = new ForeignSapVbakDTO(sapVbak, sapVbaps,
				sapVbkdvb, sapKna1, sapTvzbt);
		vbakCache.put(key1, vbak);
		vbakCache.put(key2, vbak);
		return vbak;
	}

	private ForeignSapLikpDTO getLikpFromSap(String vbeln) throws Exception {
		JCoFunction function = getFunction(Constant.SapFunction.FIND_LIKP);
		JCoParameterList list = function.getImportParameterList();
		list.setValue("I_VBELN", vbeln);
		execute(function);

		list = function.getExportParameterList();
		SapLikpDTO sapLikp = CommonSapUtil.convert(list.getStructure("E_LIKP"),
				SapLikpDTO.class);
		SapKna1DTO sapKna1 = CommonSapUtil.convert(list.getStructure("E_KNA1"),
				SapKna1DTO.class);
		List<SapLipsDTO> sapLipss = CommonSapUtil.convert(list.getTable("EST_LIPS"),
				SapLipsDTO.class);
		String key1 = StringUtils.trim(list.getString("E_VBELN_1"));
		String key2 = StringUtils.trim(list.getString("E_VBELN_2"));

		ForeignSapLikpDTO likp = new ForeignSapLikpDTO(sapLikp, sapLipss,
				sapKna1);
		likpCache.put(key1, likp);
		likpCache.put(key2, likp);
		return likp;
	}

	@Override
	public void writeCreditPost(String vbeln) throws Exception {
		JCoFunction function = getFunction(Constant.SapFunction.WRITE_CREDITPOST);
		JCoParameterList list = function.getImportParameterList();
		list.setValue("I_VBELN", vbeln);
		execute(function);
	}

	@Override
	public void writeStockPrepare(VbapPK pk) throws Exception {
		JCoFunction function = getFunction(Constant.SapFunction.WRITE_STOCKPREPARE);
		JCoParameterList list = function.getImportParameterList();
		list.setValue("I_VBELN", pk.getVbeln());
		list.setValue("I_POSNR", pk.getPosnr());
		execute(function);
	}

	@Override
	public void writeInvoice(String vbeln) throws Exception {
		JCoFunction function = getFunction(Constant.SapFunction.WRITE_INVOICE);
		JCoParameterList list = function.getImportParameterList();
		list.setValue("I_VBELN", vbeln);
		execute(function);
	}

	@Override
	public List<SapVbkdDTO> searchVbakByBstkd(String searchBstkd)
			throws Exception {
		String bstkd = StringUtils.replace(searchBstkd, "*", "%");
		JCoFunction function = getFunction(Constant.SapFunction.SEARCH_VBAK);
		function.getImportParameterList().setValue("I_BSTKD", bstkd);
		execute(function);
		JCoTable table = function.getTableParameterList().getTable("ET_VBKD");
		return CommonSapUtil.convert(table, SapVbkdDTO.class);
	}

	@Override
	public String convertVbeln(String vbeln) throws Exception {
		return convertVbeln(new String[] { vbeln }).iterator().next();
	}

	@Override
	public Iterable<String> convertVbeln(String... vbelns) throws Exception {
		return convertVbeln(ImmutableSet.copyOf(vbelns));
	}

	@Override
	public Iterable<String> convertVbeln(Iterable<String> vbelns)
			throws Exception {
		JCoFunction function = getFunction(Constant.SapFunction.VBELN_CONVERT);
		JCoTable table = function.getImportParameterList().getTable("IT_VBELN");
		for (String vbeln : vbelns) {
			table.appendRow();
			table.setValue("VBELN", vbeln);
		}
		execute(function);
		table = function.getExportParameterList().getTable("ET_VBELN");
		ImmutableSet.Builder<String> builder = ImmutableSet.builder();
		do {
			builder.add(table.getString("VBELN"));
		} while (table.nextRow());
		return builder.build();
	}

	@Override
	protected Map<String, JCoServerFunctionHandler> getHandlerMap() {
		Map<String, JCoServerFunctionHandler> map = Maps.newHashMap();
		for (JCoServerFunctionHandler handler : context.getBeansOfType(
				JCoServerFunctionHandler.class).values()) {
			FunctionHandler anno = handler.getClass()
					.getAnnotation(FunctionHandler.class);
			if (anno != null)
				map.put(anno.functionName(), handler);
		}
		return map;
	}

}
