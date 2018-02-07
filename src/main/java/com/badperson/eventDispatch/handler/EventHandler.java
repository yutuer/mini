package com.badperson.eventDispatch.handler;

import org.springframework.beans.factory.InitializingBean;

import com.badperson.eventDispatch.eventObject.IExcelEvent;

public abstract class EventHandler<T extends IExcelEvent> implements IEventHandler<T>, InitializingBean{

	public EventHandler() {
		super();
	}

	protected abstract void init();

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

}
