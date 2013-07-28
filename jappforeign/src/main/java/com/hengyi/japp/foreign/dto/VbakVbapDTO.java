package com.hengyi.japp.foreign.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;
import com.hengyi.japp.foreign.Constant;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbakVbapDTO")
public class VbakVbapDTO implements Serializable {
	private static final long serialVersionUID = 4465409863337774384L;
	protected VbakDTO vbak;
	protected List<VbapDTO> vbaps;

	public VbakDTO getVbak() {
		return vbak;
	}

	public List<VbapDTO> getVbaps() {
		return vbaps;
	}

	public void setVbak(VbakDTO vbak) {
		this.vbak = vbak;
	}

	public void setVbaps(List<VbapDTO> vbaps) {
		this.vbaps = vbaps;
	}

	public VbakVbapDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VbakVbapDTO(VbakDTO vbak, Iterable<VbapDTO> vbaps) {
		this();
		this.vbak = vbak;
		this.vbaps = Lists.newArrayList(vbaps);
	}
}