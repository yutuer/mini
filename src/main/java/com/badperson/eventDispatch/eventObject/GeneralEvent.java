package com.badperson.eventDispatch.eventObject;

import java.util.EventObject;

public class GeneralEvent extends EventObject implements IExcelEvent{

	private static final long serialVersionUID = 1L;
	
	public GeneralEvent(String name) {
		super(name);
	}

	@Override
	public String getSource() {
		return (String) super.getSource();
	}

	@Override
	public String getName() {
		return getSource();
	}

}
