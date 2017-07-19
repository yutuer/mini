package com.badperson.eventDispatch;

import com.badperson.eventDispatch.eventObject.IExcelEventObject;

public interface IDispatcher {
	
	void dispatch(IExcelEventObject eventObject);
	
}
