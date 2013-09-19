package com.hengyi.japp.trans;

import javax.inject.Named;
import javax.inject.Singleton;

import com.hengyi.japp.common.CommonUrlUtil;

@Named
@Singleton
public class UrlUtil extends CommonUrlUtil {
	public String getYsDeliveryNewPath() {
		return "/ys/delivery";
	}

	public String getYsDeliveryUpdateView() {
		return "/faces/ys/delivery/update.jsf";
	}

	public String getYsDeliveriesPath() {
		return "/ys/deliveries";
	}

	public String getYsDeliverysView() {
		return "/faces/ys/delivery/list.jsf";
	}

	public String getYsPickNewPath() {
		return "/ys/pick";
	}

	public String getYsPickUpdateView() {
		return "/faces/ys/pick/update.jsf";
	}

	public String getYsPicksPath() {
		return "/ys/picks";
	}

	public String getYsPicksView() {
		return "/faces/ys/pick/list.jsf";
	}

	public String getCommunicateeNewPath() {
		return "/communicatee";
	}

	public String getCommunicateeUpdateView() {
		return "/faces/communicatee/update.jsf";
	}

	public String getCommunicateesPath() {
		return "/communicatees";
	}

	public String getCommunicateesView() {
		return "/faces/communicatee/list.jsf";
	}

	public String getIndicatorValueNewPath() {
		return "/indicatorValue";
	}

	public String getIndicatorValueUpdateView() {
		return "/faces/indicatorValue/update.jsf";
	}

	public String getIndicatorValuesPath() {
		return "/indicatorValues";
	}

	public String getIndicatorValuesView() {
		return "/faces/indicatorValue/list.jsf";
	}

	public String getCustomerIndicatorNewPath() {
		return "/customer/indicator";
	}

	public String getCustomerIndicatorUpdateView() {
		return "/faces/customer/indicator/update.jsf";
	}

	public String getCustomerIndicatorsPath() {
		return "/customer/indicators";
	}

	public String getCustomerIndicatorsView() {
		return "/faces/customer/indicator/list.jsf";
	}

	public String getCustomerNewPath() {
		return "/customer";
	}

	public String getCustomerUpdateView() {
		return "/faces/customer/update.jsf";
	}

	public String getCustomersPath() {
		return "/customers";
	}

	public String getCustomersView() {
		return "/faces/customer/list.jsf";
	}

	public String getStorageIndicatorNewPath() {
		return "/storage/indicator";
	}

	public String getStorageIndicatorUpdateView() {
		return "/faces/storage/indicator/update.jsf";
	}

	public String getStorageIndicatorsPath() {
		return "/storage/indicators";
	}

	public String getStorageIndicatorsView() {
		return "/faces/storage/indicator/list.jsf";
	}

	public String getStorageNewPath() {
		return "/storage";
	}

	public String getStorageUpdateView() {
		return "/faces/storage/update.jsf";
	}

	public String getStoragesPath() {
		return "/storages";
	}

	public String getStoragesView() {
		return "/faces/storage/list.jsf";
	}
}
