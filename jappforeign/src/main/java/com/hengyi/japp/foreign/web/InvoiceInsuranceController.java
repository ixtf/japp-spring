package com.hengyi.japp.foreign.web;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class InvoiceInsuranceController extends InvoiceController {
	private static final long serialVersionUID = 7861432996880756629L;

	@Override
	public String open() {
		if (!invoiceRepository.exists(getNumber())) {
			addRootErrorMessage(new Exception(getNumber() + "不存在！"));
			return null;
		}
		return "pretty:invoiceInsuranceUpdate";
	}

	@Override
	public String save() {
		try {
			// invoiceModel.getInsuranceInfo().setOperator(
			// cacheService.getCurrentOperator());
			// invoiceService.saveInvoiceInsuranceInfo(invoiceModel);
			addInfoMessage("保存成功！");
			return "pretty:invoiceInsuranceManage";
		} catch (Exception e) {
			addRootErrorMessage(e);
		}
		return null;
	}

	public String openInvoiceInsurance() {
		if (!invoiceRepository.exists(getNumber())) {
			addRootErrorMessage(new Exception(getNumber() + "不存在！"));
			return "pretty:invoiceInsuranceManage";
		}

		try {
			// invoiceModel = invoiceService.findOneInvoiceModel(getNumber());
		} catch (Exception e) {
			addRootErrorMessage(e);
			return "pretty:invoiceInsuranceManage";
		}
		return null;
	}
}
