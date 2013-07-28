package com.hengyi.japp.foreign.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.common.sap.dto.SapKna1DTO;
import com.hengyi.japp.common.sap.dto.SapLikpDTO;
import com.hengyi.japp.common.sap.dto.SapLipsDTO;
import com.hengyi.japp.foreign.Constant;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "ForeignSapLikpDTO")
public class ForeignSapLikpDTO implements Serializable {
	private static final long serialVersionUID = 3266044890004344634L;

	private final SapLikpDTO sapLikp;
	private final List<SapLipsDTO> sapLipss;
	private final SapKna1DTO sapKna1;

	public ForeignSapLikpDTO(SapLikpDTO sapLikp, List<SapLipsDTO> sapLipss,
			SapKna1DTO sapKna1) {
		super();
		this.sapLikp = sapLikp;
		this.sapLipss = sapLipss;
		this.sapKna1 = sapKna1;
	}

	public SapLikpDTO getSapLikp() {
		return sapLikp;
	}

	public List<SapLipsDTO> getSapLipss() {
		return sapLipss;
	}

	public SapKna1DTO getSapKna1() {
		return sapKna1;
	}
}