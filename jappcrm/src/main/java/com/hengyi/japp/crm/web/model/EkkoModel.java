package com.hengyi.japp.crm.web.model;

import java.util.List;

import com.hengyi.japp.common.sap.dto.SapEkkoDTO;

public class EkkoModel {
	private SapEkkoDTO ekko;
	private List<EkpoModel> ekpos;

	public SapEkkoDTO getEkko() {
		return ekko;
	}

	public void setEkko(SapEkkoDTO ekko) {
		this.ekko = ekko;
	}

	public List<EkpoModel> getEkpos() {
		return ekpos;
	}

	public void setEkpos(List<EkpoModel> ekpos) {
		this.ekpos = ekpos;
	}
}
