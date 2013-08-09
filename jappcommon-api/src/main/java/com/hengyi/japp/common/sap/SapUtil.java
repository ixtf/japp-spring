package com.hengyi.japp.common.sap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.collect.Lists;
import com.sap.conn.jco.JCoRecord;
import com.sap.conn.jco.JCoTable;

public final class SapUtil {
	public static <T> T convert(JCoRecord record, Class<T> clazz)
			throws Exception {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
		return convert(record, clazz, pds);
	}

	public static <T> List<T> convert(JCoTable table, Class<T> clazz)
			throws Exception {
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
		List<T> result = Lists.newArrayList();
		if (!table.isEmpty()) {
			do {
				JCoRecord record = table;
				result.add(convert(record, clazz, pds));
			} while (table.nextRow());
		}
		return result;
	}

	public static <T> T convert(JCoRecord record, Class<T> clazz,
			PropertyDescriptor[] pds) throws Exception {
		T t = clazz.newInstance();
		for (PropertyDescriptor pd : pds) {
			String fieldName = pd.getName();
			if (pd.getWriteMethod() == null)
				continue;
			/*
			 * TODO
			 * 
			 * 可能会有点问题，sap的DTO里如果有联合主键，而联合主键里继续有Class，就会有问题
			 */
			Object value;
			if (fieldName.equals("pk")) {
				Method m = pd.getWriteMethod();
				Class<?> pkClass = m.getParameterTypes()[0];
				value = convert(record, pkClass);
			} else {
				value = record.getValue(fieldName.toUpperCase());
			}
			PropertyUtils.setSimpleProperty(t, fieldName, value);
		}
		return t;
	}

}
