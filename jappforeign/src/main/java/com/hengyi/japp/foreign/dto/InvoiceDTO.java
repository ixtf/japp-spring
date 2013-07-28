package com.hengyi.japp.foreign.dto;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.dto.common.InvoiceCommonDTO;
import com.hengyi.japp.foreign.dto.common.LikpCommonDTO;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "InvoiceDTO")
public class InvoiceDTO extends InvoiceCommonDTO {
	private static final long serialVersionUID = 6579264331656888415L;
	private Set<LikpCommonDTO> likps;

	public Set<LikpCommonDTO> getLikps() {
		return likps;
	}

	public void setLikps(Set<LikpCommonDTO> likps) {
		this.likps = likps;
	}
}
