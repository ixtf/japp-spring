package com.hengyi.japp.trans.web.ys;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.trans.domain.ys.YsDelivery;
import com.hengyi.japp.trans.web.AbstractController;

@Named
@Scope("view")
public class YsDeliveryController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 6534950181814661601L;
	private String uuid;
	private YsDelivery ysDelivery;

	public void save() {
	}

	public String getUuid() {
		return uuid;
	}

	public YsDelivery getYsDelivery() {
		return ysDelivery;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setYsDelivery(YsDelivery ysDelivery) {
		this.ysDelivery = ysDelivery;
	}
}
