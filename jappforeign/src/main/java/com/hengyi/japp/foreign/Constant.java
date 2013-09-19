package com.hengyi.japp.foreign;

public final class Constant extends com.hengyi.japp.common.CommonConstant {
	public static final String NAME_SPACE = "com.hengyi.japp.foreign";

	public static class SapFunction {
		public static final String FIND_VBAK = "ZJAPP_FOREIGN_1";
		public static final String VBELN_ALPHA = "ZJAPP_FOREIGN_2";
		public static final String VBELN_CONVERT = "ZJAPP_FOREIGN_3";
		public static final String FIND_LIKP = "ZJAPP_FOREIGN_4";
		public static final String WRITE_CREDITPOST = "ZJAPP_FOREIGN_6";
		public static final String WRITE_STOCKPREPARE = "ZJAPP_FOREIGN_7";
		public static final String WRITE_INVOICE = "ZJAPP_FOREIGN_8";
		public static final String SEARCH_VBAK = "ZJAPP_FOREIGN_5";
		// public static final String BIND_VBAK_CREDITPOST = "ZJAPP_FOREIGN_9";
	}

	public static class ErrorCode extends
			com.hengyi.japp.common.CommonConstant.ErrorCode {
		public static final String MODEIFY_NOT_INIT_INVOICE = "MODEIFY_NOT_INIT_INVOICE";
	}
}
