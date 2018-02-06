package com.badperson.eventDispatch.eventObject;

public class EndEvent extends GeneralExcelEvent {

	private static final long serialVersionUID = 1L;

	public EndEvent(String name, ExcelEventContext context) {
		super(name, context);
	}

}
