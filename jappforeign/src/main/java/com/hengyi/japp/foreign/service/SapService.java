package com.hengyi.japp.foreign.service;

import java.util.List;

import com.hengyi.japp.common.sap.dto.SapVbkdDTO;
import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.common.service.CommonSapService;
import com.hengyi.japp.foreign.dto.ForeignSapLikpDTO;
import com.hengyi.japp.foreign.dto.ForeignSapVbakDTO;

public interface SapService extends CommonSapService {
	ForeignSapVbakDTO findVbak(String vbeln) throws Exception;

	ForeignSapLikpDTO findLikp(String vbeln) throws Exception;

	void writeCreditPost(String vbeln) throws Exception;

	void writeStockPrepare(VbapPK pk) throws Exception;

	void writeInvoice(String vbeln) throws Exception;

	String convertVbeln(String vbeln) throws Exception;

	Iterable<String> convertVbeln(Iterable<String> vbeln) throws Exception;

	Iterable<String> convertVbeln(String... vbelns) throws Exception;

	List<SapVbkdDTO> searchVbakByBstkd(String searchBstkd) throws Exception;
}
