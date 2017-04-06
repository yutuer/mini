package com.badperson.util;

import java.util.Collection;
import java.util.Properties;

public class PropertiesReader {
	
	public static Collection<String> getAllKeys(String fileName) {
		if(!fileName.endsWith(".properties")){
			throw new RuntimeException("file suffix error!!");
		}
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
		return prop.stringPropertyNames();
	}
	
}	
