package com.hengyi.japp.common;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.PropertyUtils;
import org.dozer.Mapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hengyi.japp.common.service.AbstractUrlService;

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

	public static <K, V> Map<K, List<V>> listMap(Iterable<V> vs, String id)
			throws Exception {
		Map<K, List<V>> result = Maps.newHashMap();
		for (V v : vs) {
			K k = (K) PropertyUtils.getSimpleProperty(v, id);
			List<V> list = result.get(k);
			if (list == null) {
				list = Lists.newArrayList();
				list.add(v);
				result.put(k, list);
			} else
				list.add(v);
		}
		return result;
	}

	public static <T> List<T> convert(Mapper dozer, Iterable<?> vs,
			Class<T> clazz) {
		List<T> result = Lists.newArrayList();
		for (Object o : vs)
			result.add(dozer.map(o, clazz));
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

	public static AbstractUrlService urlService(final String newPath) {
		return new AbstractUrlService() {

			@Override
			public String getNewPath() {
				return newPath;
			}

		};
	}
}
