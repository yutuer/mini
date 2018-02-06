package com.badperson.eventDispatch.handler;

import com.badperson.eventDispatch.eventObject.IExcelEvent;

public interface IEventHandler<T extends IExcelEvent> {

	void doHandler(T event) throws Exception;
	
}
