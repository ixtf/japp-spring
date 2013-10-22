package com.hengyi.japp.common.web;

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
}
