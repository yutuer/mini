package com.badperson.eventDispatch;

import com.badperson.eventDispatch.eventObject.IExcelEvent;

public interface IDispatcher {
	
	void dispatch(IExcelEvent eventObject);
	
}
