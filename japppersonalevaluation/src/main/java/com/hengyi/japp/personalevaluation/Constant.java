package com.hengyi.japp.personalevaluation;


public final class Constant extends com.hengyi.japp.common.CommonConstant {
	public static final String SESSION_TASK = "__SESSION_TASK__";
	public static final int _YEAR = 2013;

	public static class URL {
		public static final String HOME = "/";
		public static final String LOGIN = "/login";
		public static final String LOGOUT = "/logout";

		public static final String TASKS = "/tasks";
		public static final String TASK_NEW = "/task";
		public static final String TASK_UPDATE = "/tasks/{nodeId}";
		public static final String TASK_CHANGE = "/taskChange";
		public static final String THEME = "/theme";
	}

	public static class JSF {
		public static final String HOME = "/faces/home.jsf";
		public static final String LOGIN = "/faces/login.jsf";

		public static final String TASKS = "/faces/task/list.jsf";
		public static final String TASK_UPDATE = "/faces/task/update.jsf";
		public static final String TASK_CHANGE = "/faces/task/change.jsf";
		public static final String THEME = "/faces/theme.jsf";
	}

	public static final class ErrorCode extends
			com.hengyi.japp.common.CommonConstant.ErrorCode {
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
