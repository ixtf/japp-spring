package com.hengyi.japp.trans.web.ys;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.trans.domain.ys.YsDelivery;
import com.hengyi.japp.trans.web.AbstractController;
import com.hengyi.japp.trans.web.data.YsDeliveryModel;

@Named
@Scope("view")
public class YsDeliverysController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 6534950181814661601L;
	private YsDeliveryModel ysDeliverys;
	private YsDelivery ysDelivery;

	public YsDeliveryModel getYsDeliverys() {
		return ysDeliverys;
	}

	public YsDelivery getYsDelivery() {
		return ysDelivery;
	}

	public void setYsDeliverys(YsDeliveryModel ysDeliverys) {
		if (ysDeliverys == null)
			ysDeliverys = new YsDeliveryModel(ysDeliveryService);
		this.ysDeliverys = ysDeliverys;
	}

	public void setYsDelivery(YsDelivery ysDelivery) {
		this.ysDelivery = ysDelivery;
	}
}
