package com.badperson.eventDispatch.eventObject;

public class GeneralExcelEvent extends GeneralEvent{

	private static final long serialVersionUID = 1L;

	private final ExcelEventContext context;
	
	public GeneralExcelEvent(String name, ExcelEventContext context) {
		super(name);
		this.context = context;
	}

	public ExcelEventContext getContext() {
		return context;
	}
	
}
