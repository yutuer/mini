package com.badperson.util;

import java.io.InputStream;

public class LoadResource {
	
	public static InputStream getResourceAsStream(String fileName){
		ClassLoader cl = ClassLoader.getSystemClassLoader();
//		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		return cl.getResourceAsStream(fileName);
	}
	
}
