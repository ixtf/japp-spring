package com.hengyi.japp.crm.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.hengyi.japp.crm.data.CrmType;

public class CustomerDTO extends CrmDTO {
	private static final long serialVersionUID = 1L;
	@NotBlank
	private String mainBusiness;
	@NotBlank
	private String coBusiness;

	public String getMainBusiness() {
		return mainBusiness;
	}

	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	public String getCoBusiness() {
		return coBusiness;
	}

	public void setCoBusiness(String coBusiness) {
		this.coBusiness = coBusiness;
	}

	@Override
	public CrmType getCrmType() {
		return CrmType.CUSTOMER;
	}
}
