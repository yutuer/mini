package com.badperson.eventDispatch.eventObject;

import java.util.EventObject;

public class GeneralEvent<T> extends EventObject implements IExcelEvent {

	public GeneralEvent(T source) {
		super(source);
	}

	@Override
	public T getSource() {
		return (T) super.getSource();
	}

}
