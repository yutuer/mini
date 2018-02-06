package com.badperson.eventDispatch.eventObject.eventSource;

public class BeginEventSource {

	private String excelName;

	public BeginEventSource(String excelName) {
		super();
		this.excelName = excelName;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

}
