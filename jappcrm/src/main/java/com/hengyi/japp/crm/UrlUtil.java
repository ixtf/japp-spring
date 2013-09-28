package com.hengyi.japp.crm;

import javax.inject.Named;
import javax.inject.Singleton;

import com.hengyi.japp.common.CommonUrlUtil;

@Named
@Singleton
public class UrlUtil extends CommonUrlUtil {
	public String getCrmTypeNewPath() {
		return "/crmType";
	}

	public String getCrmTypeUpdateView() {
		return "/faces/crmType/update.jsf";
	}

	public String getCrmTypesPath() {
		return "/crmTypes";
	}

	public String getCrmTypesView() {
		return "/faces/crmType/list.jsf";
	}

	public String getCertificateNewPath() {
		return "/certificate";
	}

	public String getCertificateUpdateView() {
		return "/faces/certificate/update.jsf";
	}

	public String getCertificatesPath() {
		return "/certificates";
	}

	public String getCertificatesView() {
		return "/faces/certificate/list.jsf";
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

	public String getCustomerReportNewPath() {
		return "/customer/report";
	}

	public String getCustomerReportUpdateView() {
		return "/faces/customer/report/update.jsf";
	}

	public String getCustomerReportsPath() {
		return "/customer/reports";
	}

	public String getCustomerReportsView() {
		return "/faces/customer/report/list.jsf";
	}

	public String getStorageReportNewPath() {
		return "/storage/report";
	}

	public String getStorageReportUpdateView() {
		return "/faces/storage/report/update.jsf";
	}

	public String getStorageReportsPath() {
		return "/storage/reports";
	}

	public String getStorageReportsView() {
		return "/faces/storage/report/list.jsf";
	}
}
