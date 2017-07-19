package com.badperson.eventDispatch.listener;

import java.util.EventListener;
import java.util.EventObject;

public interface ExcelEventListener extends EventListener{
	
	public void onEvent(EventObject eventObject);
}
