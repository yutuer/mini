package com.badperson.util;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.BeanFactory;

public class SpringUtil {

	public static <T> T getBean(BeanFactory bf, Class<T> c, Object... args) {
		return (T) bf.getBean(getStringClassName(c), args);
	}

	public static String getStringClassName(Class<?> c) {
		return c.getName();
	}
}
