package com.hengyi.japp.common.web;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;

public class FacesAccessor {
	public static MethodExpression createMethodExpression(
			String valueExpression, Class<?> expectedReturnType,
			Class<?>[] expectedParamTypes) {
		MethodExpression methodExpression = null;
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			ExpressionFactory factory = fc.getApplication()
					.getExpressionFactory();
			methodExpression = factory.createMethodExpression(
					fc.getELContext(), valueExpression, expectedReturnType,
					expectedParamTypes);
		} catch (Exception e) {
			throw new FacesException("Method expression '" + valueExpression
					+ "' could not be created.");
		}

		return methodExpression;
	}

	public static MethodExpressionActionListener createMethodActionListener(
			String valueExpression, Class<?> expectedReturnType,
			Class<?>[] expectedParamTypes) {
		MethodExpressionActionListener actionListener = null;
		try {
			actionListener = new MethodExpressionActionListener(
					createMethodExpression(valueExpression, expectedReturnType,
							expectedParamTypes));
		} catch (Exception e) {
			throw new FacesException("Method expression for ActionListener '"
					+ valueExpression + "' could not be created.");
		}

		return actionListener;
	}

	public static Object getManagedBean(final String beanName) {
		FacesContext fc = FacesContext.getCurrentInstance();

		Object bean;
		try {
			ELContext elContext = fc.getELContext();
			bean = elContext.getELResolver()
					.getValue(elContext, null, beanName);
		} catch (RuntimeException e) {
			throw new FacesException(e.getMessage(), e);
		}

		if (bean == null) {
			throw new FacesException(
					"Managed bean with name '"
							+ beanName
							+ "' was not found. Check your faces-config.xml or @ManagedBean annotation.");
		}

		return bean;
	}

	public static String getRequestParameter(String name) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

}
