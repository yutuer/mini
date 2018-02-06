package com.badperson.eventDispatch.handler;

import com.badperson.eventDispatch.eventObject.IExcelEvent;

public abstract class EventHandler<T extends IExcelEvent> implements IEventHandler<T> {

	public EventHandler() {
		super();
	}

	protected abstract void init();

}
