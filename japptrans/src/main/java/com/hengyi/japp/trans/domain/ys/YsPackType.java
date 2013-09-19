package com.hengyi.japp.trans.domain.ys;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hengyi.japp.trans.domain.PackType;

@Entity
@Table(name = "t_ys_packType")
public class YsPackType extends PackType {
	private static final long serialVersionUID = 3159874798335827123L;
}
