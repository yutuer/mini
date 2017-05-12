package com.badperson.util;

import java.util.Collection;
import java.util.Properties;

public class PropertiesReader {

	private String fileName;
	private Properties prop;

	public PropertiesReader(String fileName) {
		super();
		if (!fileName.endsWith(".properties")) {
			throw new RuntimeException("file suffix error!!");
		}
		this.fileName = fileName;
	}

	public Properties getProperties() {
		if (prop == null) {
			prop = new Properties();
			try {
				ClassLoader cl = Thread.currentThread().getContextClassLoader();
				prop.load(cl.getResourceAsStream(fileName));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return prop;
	}

	public String getProperValue(String key, RuntimeException e) {
		String value = getProperties().getProperty(key);
		if (value == null && e != null) {
			throw e;
		}
		return value;
	}

	public Collection<String> getAllKeys() {
		return getProperties().stringPropertyNames();
	}

}