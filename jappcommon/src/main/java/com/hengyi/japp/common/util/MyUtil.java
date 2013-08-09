package com.hengyi.japp.common.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.hengyi.japp.common.data.PrincipalType;

public class MyUtil {
	public static String newUuid() {
		return UUID.randomUUID().toString();
	}

	public static String trimUpper(String s) {
		return StringUtils.trim(s).toUpperCase();
	}

	public static String password(String password, Object salt) {
		SimpleHash hash = new SimpleHash("SHA-512", password, salt, 5000);
		return hash.toHex();
	}

	public static Object salt(PrincipalType principalType, String principal) {
		return new StringBuilder().append(principalType).append(principal)
				.toString();
	}
}
