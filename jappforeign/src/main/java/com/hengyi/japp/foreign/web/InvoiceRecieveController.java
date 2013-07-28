package com.hengyi.japp.foreign.web;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class InvoiceRecieveController extends InvoiceController {
	private static final long serialVersionUID = 7861432996880756629L;

	@Override
	public String open() {
		if (!invoiceRepository.exists(getNumber())) {
			addRootErrorMessage(new Exception(getNumber() + "不存在！"));
			return null;
		}
		return "pretty:invoiceRecieveUpdate";
	}

	@Override
	public String save() {
		try {
			// invoiceModel.getRecieveInfo().setOperator(
			// cacheService.getCurrentOperator());
			// invoiceService.saveInvoiceRecieveInfo(invoiceModel);
			addInfoMessage("保存成功！");
			return "pretty:invoiceRecieveManage";
		} catch (Exception e) {
			addRootErrorMessage(e);
		}
		return null;
	}

	public String openInvoiceRecieve() {
		if (!invoiceRepository.exists(getNumber())) {
			addRootErrorMessage(new Exception(getNumber() + "不存在！"));
			return "pretty:invoiceRecieveManage";
		}

		try {
			// invoiceModel = invoiceService.findOneInvoiceModel(getNumber());
		} catch (Exception e) {
			addRootErrorMessage(e);
			return "pretty:invoiceRecieveManage";
		}
		return null;
	}
}
