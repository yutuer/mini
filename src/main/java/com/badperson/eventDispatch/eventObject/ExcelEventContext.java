package com.badperson.eventDispatch.eventObject;

import java.io.Writer;

public class ExcelEventContext {
	
	private final String excelName;
	private Writer excelXShellwriter;
	private int index;

	public ExcelEventContext(String excelName) {
		super();
		this.excelName = excelName;
	}

	public String getExcelName() {
		return excelName;
	}

	public Writer getExcelXShellwriter() {
		return excelXShellwriter;
	}

	public void setExcelXShellwriter(Writer excelXShellwriter) {
		this.excelXShellwriter = excelXShellwriter;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
