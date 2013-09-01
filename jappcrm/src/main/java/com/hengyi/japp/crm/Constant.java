package com.hengyi.japp.crm;

import javax.inject.Named;

public final class Constant extends com.hengyi.japp.common.Constant {
	@Named
	public static final String SESSION_TASK = "__SESSION_TASK__";
	public static final int _YEAR = 2013;

	public static class URL {
		public static final String HOME = "/";
		public static final String LOGIN = "/login";
		public static final String LOGOUT = "/logout";
		public static final String THEME = "/theme";
		public static final String ADMIN = "/admin";

		public static final String COMMUNICATEE_NEW = "/communicatee";
		public static final String COMMUNICATEES = "/communicatees";
		public static final String CRMTYPE_NEW = "/crmType";
		public static final String CRMTYPES = "/crmTypes";
		public static final String INDICATORS = "/indicators";
		public static final String INDICATORVALUE_NEW = "/indicatorValue";
		public static final String INDICATORVALUES = "/indicatorValues";
		public static final String CUSTOMER_NEW = "/customer";
		public static final String CUSTOMERS = "/customers";
		public static final String STORAGE_NEW = "/storage";
		public static final String STORAGES = "/storages";
		public static final String CUSTOMER_INDICATOR_NEW = "/customer/indicator";
		public static final String CUSTOMER_INDICATORS = "/customer/indicators";
		public static final String STORAGE_INDICATOR_NEW = "/storage/indicator";
		public static final String STORAGE_INDICATORS = "/storage/indicators";
	}

	public static class JSF {
		public static final String HOME = "/faces/home.jsf";
		public static final String LOGIN = "/faces/login.jsf";
		public static final String THEME = "/faces/theme.jsf";
		public static final String ADMIN = "/faces/admin/index.jsf";

		public static final String COMMUNICATEE_NEW = "/faces/communicatee/update.jsf";
		public static final String COMMUNICATEES = "/faces/communicatee/list.jsf";
		public static final String CRMTYPE_NEW = "/faces/crmType/update.jsf";
		public static final String CRMTYPES = "/faces/crmType/list.jsf";
		public static final String INDICATORS = "/faces/indicators.jsf";
		public static final String INDICATORVALUE_NEW = "/faces/indicatorValue/update.jsf";
		public static final String INDICATORVALUES = "/faces/indicatorValue/list.jsf";
		public static final String CUSTOMER_NEW = "/faces/customer/update.jsf";
		public static final String CUSTOMERS = "/faces/customer/list.jsf";
		public static final String CUSTOMER_INDICATOR_NEW = "/faces/customer/indicator/update.jsf";
		public static final String CUSTOMER_INDICATORS = "/faces/customer/indicator/list.jsf";
		public static final String STORAGE_NEW = "/faces/storage/indicator/update.jsf";
		public static final String STORAGES = "/faces/storage/indicator/list.jsf";
	}

	public static final class ErrorCode extends
			com.hengyi.japp.common.Constant.ErrorCode {
		public static final String ERROR_JSON_CONVERT = "error.json.convert";
		public static final String ERROR_NOT_SUBMIT = "error.not.submit";
		public static final String ERROR_NOT_MANAGER = "error.not.manager";
		public static final String ERROR_NOT_ACTIVE = "error.not.active";
		public static final String ERROR_ALREADY_SUBMIT = "error.already.submit";
		public static final String ERROR_NOT_SELF = "error.not.self";
		public static final String ERROR_ALREADY_ACTIVE = "error.already.active";
		public static final String ERROR_WORK_BLANK = "error.work.blank";
		public static final String ERROR_NOT_ALL_EVALUATION = "error.not.all.evaluation";
		public static final String ERROR_SUBMITLIMIT = "error.submitlimit";
		public static final String ERROR_NOT_CHARGER = "error.not.charger";
		public static final String ERROR_NOT_ADMIN = "error.not.admin";
		public static final String ERROR_TASK_NOT_INIT = "error.task.not.init";
		public static final String ERROR_NOT_PERSON = "error.not.person";
		public static final String ERROR_NOT_INIT = "error.not.init";
		public static final String ERROR_NO_TASK_CONFIG = "error.no.task.config";
		public static final String ERROR_NO_TASK_KPIS = "error.no.task.kpis";
		public static final String ERROR_NO_TASK_PERSONS = "error.no.task.persons";
		public static final String ERROR_NO_TASK = "error.no.task";
		public static final String ERROR_IS_INIT = "error.is.init";
		public static final String ERROR_NO_AUTHORIZATION = "error.no.authorization";
		public static final String ERROR_NO_EVALUABLE = "error.no.evaluable";
		public static final String ERROR_AUTHENTICATION = "error.authentication";
		public static final String ERROR_TASK_FINISHED = "error.task.finished";
	}
}
