package com.hengyi.japp.foreign.web;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;

import com.hengyi.japp.foreign.domain.Invoice;
import com.hengyi.japp.foreign.domain.InvoiceInsuranceInfo;
import com.hengyi.japp.foreign.domain.InvoiceRecieveInfo;

@Named
@Scope("session")
public class InvoiceController extends AbstractController {
	private static final long serialVersionUID = 3070081187925279004L;
	@NotBlank
	protected String number;
	private String likpVbeln;

	public String open() {
		init();
		return "pretty:invoiceUpdate";
	}

	public String save() {
		try {
			// invoiceModel.getInvoice().setOperator(
			// cacheService.getCurrentOperator());
			// invoiceService.saveInvoice(invoiceModel);
			addInfoMessage("保存成功！");
			return "pretty:invoiceManage";
		} catch (Exception e) {
			addRootErrorMessage(e);
		}
		return null;
	}

	public void openInvoice() {
		// if (invoiceModel != null
		// && getInvoice().getNumber().equals(getNumber()))
		// return;

		init();
		try {
			// invoiceModel = invoiceService.findOneInvoiceModel(getNumber());
		} catch (Exception e) {
			addRootErrorMessage(e);
			init();
		}
	}

	public void addLikp() {
		try {
			// LikpModel likpModel =
			// likpService.findOneLikpModel(getLikpVbeln());
			// if (!invoiceModel.getLikpModels().contains(likpModel))
			// invoiceModel.getLikpModels().add(likpModel);
			// setLikpVbeln(likpModel.getLikp().getVbeln());
		} catch (Exception e) {
			addRootErrorMessage(e);
		}
	}

	public void removeLikp() {
		// invoiceModel.getLikpModels().remove(selectedLikpModel);
	}

	public Invoice getInvoice() {
		return null;
		// return invoiceModel.getInvoice();
	}

	public InvoiceRecieveInfo getRecieveInfo() {
		return null;
		// return invoiceModel.getRecieveInfo();
	}

	public InvoiceInsuranceInfo getInsuranceInfo() {
		return null;
		// return invoiceModel.getInsuranceInfo();
	}

	public String getNumber() {
		return StringUtils.trimToEmpty(number).toUpperCase();
	}

	public String getLikpVbeln() {
		return StringUtils.trimToEmpty(likpVbeln);
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setLikpVbeln(String likpVbeln) {
		this.likpVbeln = likpVbeln;
	}

	@PostConstruct
	public void init() {
		likpVbeln = null;
	}
}
