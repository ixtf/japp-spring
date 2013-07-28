package com.hengyi.japp.foreign.ws;

import java.util.List;

import javax.jws.WebService;

import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.foreign.dto.LikpDTO;
import com.hengyi.japp.foreign.dto.VbakDTO;
import com.hengyi.japp.foreign.dto.VbakVbapDTO;
import com.hengyi.japp.foreign.dto.VbaksVbapsDTO;
import com.hengyi.japp.foreign.dto.VbapDTO;
import com.hengyi.japp.foreign.dto.common.InvoiceCommonDTO;

@WebService
public interface SoapService {
	void bindVbakCreditPost(String vbeln, String number, String sapPrincipal)
			throws Exception;

	void recieveCreditPost(String number, String uuid) throws Exception;

	void unrecieveCreditPost(String number, String uuid) throws Exception;

	VbakDTO findOneVbak(String vbeln) throws Exception;

	void bindVbapStockPrepare(VbapPK pk, String number, String sapPrincipal)
			throws Exception;

	void finishStockPrepare(String number, String sapPrincipal)
			throws Exception;

	void unfinishStockPrepare(String number, String sapPrincipal)
			throws Exception;

	VbapDTO findOneVbap(VbapPK pk) throws Exception;

	VbakVbapDTO findOneVbakVbap(String vbeln) throws Exception;

	VbaksVbapsDTO findAllVbakVbap(List<String> vbelns) throws Exception;

	void bindLikpInvoice(String vbeln, String number, String sapPrincipal)
			throws Exception;

	void updateInvoice(InvoiceCommonDTO invoice, String sapPrincipal)
			throws Exception;

	void insuranceInvoice(String number, String sapPrincipal) throws Exception;

	void uninsuranceInvoice(String number, String sapPrincipal)
			throws Exception;

	void recieveInvoice(String number, String sapPrincipal) throws Exception;

	void unrecieveInvoice(String number, String sapPrincipal) throws Exception;

	LikpDTO findOneLikp(String vbeln) throws Exception;

	List<LikpDTO> findAllLikp(List<String> vbelns) throws Exception;
}
