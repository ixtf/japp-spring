package com.hengyi.japp.common;

import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.collect.Maps;

@SuppressWarnings("unchecked")
public class CommonUtil {
	public final static <K, V> Map<K, V> map(Iterable<V> vs, String id)
			throws Exception {
		Map<K, V> result = Maps.newHashMap();
		for (V v : vs) {
			K k = (K) PropertyUtils.getSimpleProperty(v, id);
			result.put(k, v);
		}
		return result;
	}
}
