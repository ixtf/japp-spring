package com.hengyi.japp.foreign.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;
import com.hengyi.japp.foreign.Constant;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbaksVbapsDTO")
public class VbaksVbapsDTO implements Serializable {
	private static final long serialVersionUID = 4465409863337774384L;
	private List<VbakDTO> vbaks;
	private List<VbapDTO> vbaps;

	public List<VbakDTO> getVbaks() {
		return vbaks;
	}

	public List<VbapDTO> getVbaps() {
		return vbaps;
	}

	public void setVbaks(List<VbakDTO> vbaks) {
		this.vbaks = vbaks;
	}

	public void setVbaps(List<VbapDTO> vbaps) {
		this.vbaps = vbaps;
	}

	public VbaksVbapsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VbaksVbapsDTO(Iterable<VbakDTO> vbaks, Iterable<VbapDTO> vbaps) {
		super();
		this.vbaks = Lists.newArrayList(vbaks);
		this.vbaps = Lists.newArrayList(vbaps);
	}
}