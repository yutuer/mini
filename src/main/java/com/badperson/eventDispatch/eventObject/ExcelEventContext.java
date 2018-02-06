package com.badperson.eventDispatch.eventObject;

import java.io.Writer;

public class ExcelEventContext {
	
	private final String excelName;
	private Writer writer;
	private int index;

	public ExcelEventContext(String excelName) {
		super();
		this.excelName = excelName;
	}

	public String getExcelName() {
		return excelName;
	}
	
	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer){
		this.writer = writer;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
