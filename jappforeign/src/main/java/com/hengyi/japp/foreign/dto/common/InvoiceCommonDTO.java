package com.hengyi.japp.foreign.dto.common;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.domain.data.Status;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "InvoiceCommonDTO")
public class InvoiceCommonDTO extends ModifyInfoDTO {
	private static final long serialVersionUID = 7071184289293397862L;
	protected String number;
	protected Date deliveryDate;
	protected Date etd;
	protected Date eta;
	protected Status status;
	protected InvoiceRecieveInfoDTO recieveInfo;
	protected InvoiceInsuranceInfoDTO insuranceInfo;

	public String getNumber() {
		return number;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public Date getEtd() {
		return etd;
	}

	public Date getEta() {
		return eta;
	}

	public Status getStatus() {
		return status;
	}

	public InvoiceRecieveInfoDTO getRecieveInfo() {
		return recieveInfo;
	}

	public InvoiceInsuranceInfoDTO getInsuranceInfo() {
		return insuranceInfo;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setEtd(Date etd) {
		this.etd = etd;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setRecieveInfo(InvoiceRecieveInfoDTO recieveInfo) {
		this.recieveInfo = recieveInfo;
	}

	public void setInsuranceInfo(InvoiceInsuranceInfoDTO insuranceInfo) {
		this.insuranceInfo = insuranceInfo;
	}
}