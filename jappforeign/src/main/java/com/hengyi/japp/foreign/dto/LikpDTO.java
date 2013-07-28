package com.hengyi.japp.foreign.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.dto.common.InvoiceCommonDTO;
import com.hengyi.japp.foreign.dto.common.LikpCommonDTO;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "LikpDTO")
public class LikpDTO extends LikpCommonDTO {
	private static final long serialVersionUID = 4465409863337774384L;
	private InvoiceCommonDTO invoice;

	public InvoiceCommonDTO getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceCommonDTO invoice) {
		this.invoice = invoice;
	}

}