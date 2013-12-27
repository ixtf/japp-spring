package com.hengyi.japp.crm.web.model;

import com.hengyi.japp.common.sap.dto.SapEkpoDTO;
import com.hengyi.japp.common.sap.dto.SapKonpDTO;

public class EkpoModel {
	private SapEkpoDTO ekpo;
	private SapKonpDTO konp;

	public SapEkpoDTO getEkpo() {
		return ekpo;
	}

	public void setEkpo(SapEkpoDTO ekpo) {
		this.ekpo = ekpo;
	}

	public SapKonpDTO getKonp() {
		return konp;
	}

	public void setKonp(SapKonpDTO konp) {
		this.konp = konp;
	}
}
