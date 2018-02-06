package com.badperson.eventDispatch.eventObject;

import com.badperson.eventDispatch.eventObject.eventSource.RowEventSource;

public class RowDataEvent extends GeneralExcelEvent {

	private static final long serialVersionUID = 1L;
	private final RowEventSource rowEventSource;

	public RowDataEvent(String name,  ExcelEventContext context, RowEventSource rowEventSource) {
		super(name, context);
		this.rowEventSource = rowEventSource;
	}

	public RowEventSource getRowEventSource() {
		return rowEventSource;
	}

}
