package com.badperson.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.badperson.config.Config;
import com.badperson.moduleWrite.interfaces.IDataAccess;
import com.badperson.moduleWrite.interfaces.IParse;
import com.badperson.moduleWrite.writerParse.ServerExcelWriter;
import com.google.common.collect.Maps;

import excelParse.ExcelDataAccess;
import excelParse.ExcelParse;

public class FileUtil {

	private static Map<String, ServerExcelWriter> writers;

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

	public static void init() {
		PropertiesReader pr = new PropertiesReader(Config.PORT_FILE);
		for (String excelName : pr.getAllKeys()) {
			String trueExcelFileName = Config.EXCEL_DIR + excelName + ".xlsx";
			IParse parse = new ExcelParse(trueExcelFileName);
			IDataAccess dataAccess = new ExcelDataAccess(parse);
			ServerExcelWriter writer = new ServerExcelWriter(dataAccess);
			writers.put(excelName, writer);
		}
	}

	public static Map<String, ServerExcelWriter> getWriters() {
		if(writers == null){
			writers = Maps.newHashMap();
			init();
		}
		return writers;
	}

	public static void deleteFile(File f) {
		if (f.exists()) {
			if (f.isDirectory()) {
				for (File file : f.listFiles()) {
					deleteFile(file);
				}
			} else {
				f.delete();
			}
		}
	}
}
