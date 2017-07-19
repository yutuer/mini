package com.badperson.eventDispatch;

import java.util.EventObject;

public interface IDispatcher {
	
	void dispatch(EventObject eventObject);
	
}
