package com.hengyi.japp.foreign.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.dto.common.CreditPostCommonDTO;
import com.hengyi.japp.foreign.dto.common.VbakCommonDTO;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "CreditPostDTO")
public class CreditPostDTO extends CreditPostCommonDTO implements Serializable {
	private static final long serialVersionUID = 6579264331656888415L;
	private List<VbakCommonDTO> vbaks;

	public List<VbakCommonDTO> getVbaks() {
		return vbaks;
	}

	public void setVbaks(List<VbakCommonDTO> vbaks) {
		this.vbaks = vbaks;
	}
}
