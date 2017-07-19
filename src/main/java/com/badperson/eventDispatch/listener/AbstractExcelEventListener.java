package com.badperson.eventDispatch.listener;

import com.badperson.eventDispatch.eventObject.BeginEventObject;
import com.badperson.eventDispatch.eventObject.EndEventObject;
import com.badperson.eventDispatch.eventObject.RowDataEventObject;
import com.badperson.eventDispatch.eventObject.StaticBeginEventObject;
import com.badperson.eventDispatch.eventObject.StaticEndEventObject;

public abstract class AbstractExcelEventListener implements ExcelEventListener {

	public void onEvent(StaticBeginEventObject eventObject) throws Exception {}
	public void onEvent(BeginEventObject eventObject) throws Exception {}
	public void onEvent(EndEventObject eventObject) throws Exception {}
	public void onEvent(RowDataEventObject eventObject) throws Exception {}
	public void onEvent(StaticEndEventObject eventObject) throws Exception {}
	
}
