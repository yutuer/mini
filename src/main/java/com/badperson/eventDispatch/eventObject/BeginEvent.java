package com.badperson.eventDispatch.eventObject;

public class BeginEvent extends GeneralExcelEvent {

	private static final long serialVersionUID = 1L;

	public BeginEvent(String name, ExcelEventContext context) {
		super(name, context);
	}

}
