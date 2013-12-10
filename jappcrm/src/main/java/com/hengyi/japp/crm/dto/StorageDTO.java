package com.hengyi.japp.crm.dto;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.hengyi.japp.crm.data.CrmType;

public class StorageDTO extends CrmDTO {
	@NotBlank
	private String mainBusiness;
	@NotBlank
	private String coBusiness;

	@Override
	public CrmType getCrmType() {
		return CrmType.STORAGE;
	}

	public String getMainBusiness() {
		return mainBusiness;
	}

	public String getCoBusiness() {
		return coBusiness;
	}

	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = StringUtils.trim(mainBusiness);
	}

	public void setCoBusiness(String coBusiness) {
		this.coBusiness = StringUtils.trim(coBusiness);
	}

}
