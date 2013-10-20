package com.hengyi.japp.common;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.collect.Maps;

@SuppressWarnings("unchecked")
public class CommonUtil {
	public static <K, V> Map<K, V> map(Iterable<V> vs, String id)
			throws Exception {
		Map<K, V> result = Maps.newHashMap();
		for (V v : vs) {
			K k = (K) PropertyUtils.getSimpleProperty(v, id);
			result.put(k, v);
		}
		return result;
	}

	public static final ResourceBundle resourceBundle(String baseName) {
		return ResourceBundle.getBundle(baseName, Locale.CHINA);
	}

	public static final ResourceBundle resourceBundle(String baseName,
			Locale locale) {
		try {
			return ResourceBundle.getBundle(baseName, locale);
		} catch (Exception e) {
			// TODO resource 需要改进
			return ResourceBundle.getBundle(baseName, Locale.CHINA);
		}
	}

	public static ResourceBundle errorResourceBundle() {
		return resourceBundle("errors");
	}

	public static ResourceBundle errorResourceBundle(Locale locale) {
		return resourceBundle("errors", locale);
	}

	public static ResourceBundle messageResourceBundle() {
		return resourceBundle("messages");
	}

	public static ResourceBundle messageResourceBundle(Locale locale) {
		return resourceBundle("messages", locale);
	}

	public static String get(String key) {
		return messageResourceBundle().getString(key);
	}

	public static String get(String key, Locale locale) {
		return messageResourceBundle(locale).getString(key);
	}
}
