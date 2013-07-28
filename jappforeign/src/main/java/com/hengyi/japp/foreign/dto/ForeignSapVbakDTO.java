package com.hengyi.japp.foreign.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.common.sap.dto.SapKna1DTO;
import com.hengyi.japp.common.sap.dto.SapTvzbtDTO;
import com.hengyi.japp.common.sap.dto.SapVbakDTO;
import com.hengyi.japp.common.sap.dto.SapVbapDTO;
import com.hengyi.japp.common.sap.dto.SapVbkdvbDTO;
import com.hengyi.japp.foreign.Constant;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "ForeignSapVbakDTO")
public final class ForeignSapVbakDTO implements Serializable {
	private static final long serialVersionUID = 3266044890004344634L;

	private final SapVbakDTO sapVbak;
	private final List<SapVbapDTO> sapVbaps;
	private final SapVbkdvbDTO sapVbkdvb;
	private final SapKna1DTO sapKna1;
	private final SapTvzbtDTO sapTvzbt;

	public ForeignSapVbakDTO(SapVbakDTO sapVbak, List<SapVbapDTO> sapVbaps,
			SapVbkdvbDTO sapVbkdvb, SapKna1DTO sapKna1, SapTvzbtDTO sapTvzbt) {
		super();
		this.sapVbak = sapVbak;
		this.sapVbaps = sapVbaps;
		this.sapVbkdvb = sapVbkdvb;
		this.sapKna1 = sapKna1;
		this.sapTvzbt = sapTvzbt;
	}

	public SapVbakDTO getSapVbak() {
		return sapVbak;
	}

	public List<SapVbapDTO> getSapVbaps() {
		return sapVbaps;
	}

	public SapVbkdvbDTO getSapVbkdvb() {
		return sapVbkdvb;
	}

	public SapKna1DTO getSapKna1() {
		return sapKna1;
	}

	public SapTvzbtDTO getSapTvzbt() {
		return sapTvzbt;
	}
}