package com.hengyi.japp.common.data;

public enum PrincipalType {
	INI("文本配置INI"), MOBILE("手机"), EMAIL("邮箱"), SSO("单点登录"), HR("HR自助平台"), SAP(
			"SAP"), OA1("OA"), OA2("OA"), QQ("QQ"), SINA_WEIBO("新浪微博");
	private final String name;

	private PrincipalType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
