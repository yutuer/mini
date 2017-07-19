package com.badperson.eventDispatch.listener;

import java.util.EventListener;

import com.badperson.eventDispatch.eventObject.IExcelEventObject;

public interface ExcelEventListener extends EventListener{
	
	public void onEvent(IExcelEventObject eventObject);
}
