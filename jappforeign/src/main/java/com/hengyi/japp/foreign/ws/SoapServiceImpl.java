package com.hengyi.japp.foreign.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.foreign.domain.Operator;
import com.hengyi.japp.foreign.dto.LikpDTO;
import com.hengyi.japp.foreign.dto.VbakDTO;
import com.hengyi.japp.foreign.dto.VbakVbapDTO;
import com.hengyi.japp.foreign.dto.VbaksVbapsDTO;
import com.hengyi.japp.foreign.dto.VbapDTO;
import com.hengyi.japp.foreign.dto.common.InvoiceCommonDTO;
import com.hengyi.japp.foreign.service.CreditPostService;
import com.hengyi.japp.foreign.service.InvoiceService;
import com.hengyi.japp.foreign.service.LikpService;
import com.hengyi.japp.foreign.service.OperatorService;
import com.hengyi.japp.foreign.service.StockPrepareService;
import com.hengyi.japp.foreign.service.VbakService;

@WebService
public class SoapServiceImpl implements SoapService {
	final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	private VbakService vbakService;
	@Inject
	private CreditPostService creditPostService;
	@Inject
	private StockPrepareService stockPrepareService;
	@Inject
	private InvoiceService invoiceService;
	@Inject
	private LikpService likpService;
	@Inject
	private OperatorService operatorService;

	@Override
	public void bindVbakCreditPost(String vbeln, String number,
			String sapPrincipal) throws Exception {
		try {
			Operator operator = operatorService.findOne(PrincipalType.SAP,
					sapPrincipal);
			creditPostService.bindVbak(number, vbeln, operator);
		} catch (Exception e) {
			log.error("销售单：{}，信用证：{}，绑定出错！", vbeln, number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public void recieveCreditPost(String number, String sapPrincipal)
			throws Exception {
		try {
			creditPostService.recieve(number,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("信用证：{}，收汇出错！", number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public void unrecieveCreditPost(String number, String sapPrincipal)
			throws Exception {
		try {
			creditPostService.unrecieve(number,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("信用证：{}，取消收汇出错！", number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public void bindVbapStockPrepare(VbapPK pk, String number,
			String sapPrincipal) throws Exception {
		try {
			stockPrepareService.bindVbap(number, pk,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("销售单：{}，行项目：{}，备货单：{}，绑定出错！", pk.getVbeln(),
					pk.getPosnr(), number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public void finishStockPrepare(String number, String sapPrincipal)
			throws Exception {
		try {
			stockPrepareService.finish(number,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("备货单：{}，完成出错！", number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public void unfinishStockPrepare(String number, String sapPrincipal)
			throws Exception {
		try {
			stockPrepareService.unfinish(number,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("备货单：{}，取消完成出错！", number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public VbakDTO findOneVbak(String vbeln) throws Exception {
		return vbakService.findOneVbak(vbeln);
	}

	@Override
	public VbapDTO findOneVbap(VbapPK pk) throws Exception {
		return vbakService.findOneVbap(pk);
	}

	@Override
	public VbakVbapDTO findOneVbakVbap(String vbeln) throws Exception {
		return vbakService.findOneVbakVbap(vbeln);
	}

	@Override
	public VbaksVbapsDTO findAllVbakVbap(List<String> vbelns) throws Exception {
		return vbakService.findAllVbakVbap(vbelns);
	}

	@Override
	public void bindLikpInvoice(String vbeln, String number, String sapPrincipal)
			throws Exception {
		try {
			invoiceService.bindLikp(number, vbeln,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("交货单：{}，发票：{}，绑定出错！", number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public void updateInvoice(InvoiceCommonDTO invoice, String sapPrincipal)
			throws Exception {
		try {
			invoiceService.update(invoice,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("发票：{}，更新数据出错！", invoice.getNumber());
			log.error("", e);
			throw e;
		}
	}

	@Override
	public void insuranceInvoice(String number, String sapPrincipal)
			throws Exception {
		try {
			invoiceService.insurance(number,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("发票：{}，投保出错！", number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public void uninsuranceInvoice(String number, String sapPrincipal)
			throws Exception {
		try {
			invoiceService.uninsurance(number,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("发票：{}，取消投保出错！", number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public void recieveInvoice(String number, String sapPrincipal)
			throws Exception {
		try {
			invoiceService.recieve(number,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("发票：{}，收汇出错！", number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public void unrecieveInvoice(String number, String sapPrincipal)
			throws Exception {
		try {
			invoiceService.unrecieve(number,
					operatorService.findOne(PrincipalType.SAP, sapPrincipal));
		} catch (Exception e) {
			log.error("发票：{}，取消收汇出错！", number);
			log.error("", e);
			throw e;
		}
	}

	@Override
	public LikpDTO findOneLikp(String vbeln) throws Exception {
		return likpService.findOneLikp(vbeln);
	}

	@Override
	public List<LikpDTO> findAllLikp(List<String> vbelns) throws Exception {
		return likpService.findAllLikp(vbelns);
	}
}
