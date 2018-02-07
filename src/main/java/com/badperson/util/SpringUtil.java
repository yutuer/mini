package com.badperson.util;

import org.springframework.beans.factory.BeanFactory;

public class SpringUtil {

	@SuppressWarnings("unchecked")
	public static <T> T getBean(BeanFactory bf, Class<T> c, Object... args) {
		return (T) bf.getBean(getStringClassName(c.getSimpleName()), args);
	}

	public static String getStringClassName(String str) {

		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
	}
}
