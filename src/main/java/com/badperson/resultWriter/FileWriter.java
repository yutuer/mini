package com.badperson.resultWriter;

public abstract class FileWriter {

	public abstract String getOutputFilePath();

	public void writeFile(String fileName) {
		if (fileName.indexOf("/") != -1 || fileName.indexOf("\\") != -1) {
			
		}
	}

}
