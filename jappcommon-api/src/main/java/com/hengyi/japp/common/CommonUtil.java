package com.hengyi.japp.common;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.context.i18n.LocaleContextHolder;

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
		Locale locale = LocaleContextHolder.getLocale();
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null)
			locale = context.getELContext().getLocale();
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

	public static ResourceBundle messageResourceBundle() {
		return resourceBundle("messages");
	}

	public static String get(String key) {
		return messageResourceBundle().getString(key);
	}
}
