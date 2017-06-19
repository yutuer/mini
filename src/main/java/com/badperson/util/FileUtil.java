package com.badperson.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	
	public static File getFile(String fileName) throws IOException {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		} else {
			file.getParentFile().mkdirs();
		}
		file.createNewFile();
		return file;
	}
}
