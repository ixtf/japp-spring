package com.hengyi.japp.crm;

import static com.hengyi.japp.common.CommonUtil.urlService;

import com.hengyi.japp.common.CommonConstant;
import com.hengyi.japp.common.service.AbstractUrlService;

public final class Constant extends CommonConstant {
	public static class SapFunction {
		public static final String INIT_KNA1 = "ZJAPP_CRM_1";
		public static final String INIT_LFA1 = "ZJAPP_CRM_5";
		public static final String CONVERT_KUNNR = "ZJAPP_CRM_4";
		public static final String CONVERT_LIFNR = "ZJAPP_CRM_6";
		public static final String F_VBAK = "ZJAPP_CRM_2";
		public static final String F_EKKO = "ZJAPP_CRM_3";
	}

	public static class Url {
		public static final AbstractUrlService corporationType = urlService("/corporationType");
		public static final AbstractUrlService certificate = urlService("/certificate");
		public static final AbstractUrlService communicatee = urlService("/communicatee");
		public static final AbstractUrlService customer = urlService("/customer");
		public static final AbstractUrlService storage = urlService("/storage");
		public static final AbstractUrlService customerIndicator = urlService("/customer/indicator");
		public static final AbstractUrlService storageIndicator = urlService("/storage/indicator");
		public static final AbstractUrlService customerReport = urlService("/customer/report");
		public static final AbstractUrlService storageReport = urlService("/storage/report");

		public static final AbstractUrlService crmField = urlService("/admin/crmField");

		public AbstractUrlService getCorporationType() {
			return corporationType;
		}

		public AbstractUrlService getCertificate() {
			return certificate;
		}

		public AbstractUrlService getCommunicatee() {
			return communicatee;
		}

		public AbstractUrlService getCustomer() {
			return customer;
		}

		public AbstractUrlService getStorage() {
			return storage;
		}

		public AbstractUrlService getCustomerIndicator() {
			return customerIndicator;
		}

		public AbstractUrlService getStorageIndicator() {
			return storageIndicator;
		}

		public AbstractUrlService getCustomerReport() {
			return customerReport;
		}

		public AbstractUrlService getStorageReport() {
			return storageReport;
		}

		public AbstractUrlService getCrmfield() {
			return crmField;
		}
	}
}
