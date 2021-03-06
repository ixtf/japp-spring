package com.hengyi.japp.crm.domain.customer;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.Crm;

@NodeEntity
public class Customer extends Crm {
	private static final long serialVersionUID = -831678558917732185L;
	@NotBlank
	@Indexed
	private String mainBusiness;
	@NotBlank
	@Indexed
	private String coBusiness;

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
