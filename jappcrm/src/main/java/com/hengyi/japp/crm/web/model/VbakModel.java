package com.hengyi.japp.crm.web.model;

import java.util.List;

import com.hengyi.japp.common.sap.dto.SapVbakDTO;
import com.hengyi.japp.common.sap.dto.SapVbapDTO;
import com.hengyi.japp.common.sap.dto.SapVbkdDTO;

public class VbakModel {
	private SapVbakDTO sapVbak;
	private SapVbkdDTO sapVbkd;
	private List<SapVbapDTO> sapVbap;

	public SapVbkdDTO getSapVbkd() {
		return sapVbkd;
	}

	public void setSapVbkd(SapVbkdDTO sapVbkd) {
		this.sapVbkd = sapVbkd;
	}

	public SapVbakDTO getSapVbak() {
		return sapVbak;
	}

	public void setSapVbak(SapVbakDTO sapVbak) {
		this.sapVbak = sapVbak;
	}

	public List<SapVbapDTO> getSapVbap() {
		return sapVbap;
	}

	public void setSapVbap(List<SapVbapDTO> sapVbap) {
		this.sapVbap = sapVbap;
	}
}
