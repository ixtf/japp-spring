package com.hengyi.japp.foreign.dto;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.dto.common.CreditPostCommonDTO;
import com.hengyi.japp.foreign.dto.common.VbakCommonDTO;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbakDTO")
public class VbakDTO extends VbakCommonDTO implements Serializable {
	private static final long serialVersionUID = 4465409863337774384L;
	private Set<CreditPostCommonDTO> creditPosts;

	public Set<CreditPostCommonDTO> getCreditPosts() {
		return creditPosts;
	}

	public void setCreditPosts(Set<CreditPostCommonDTO> creditPosts) {
		this.creditPosts = creditPosts;
	}
}