package com.hengyi.japp.foreign.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "LikpSeachDTO")
public class ForeignLikpSeachDTO implements Serializable {
	private static final long serialVersionUID = 2900598068602905949L;
}
