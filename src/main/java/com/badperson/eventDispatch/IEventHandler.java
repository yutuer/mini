package com.badperson.eventDispatch;

import com.badperson.eventDispatch.eventObject.GeneralEvent;

public interface IEventHandler<T extends GeneralEvent<Object>> {
	
	void doHandler(T event);
	
}
