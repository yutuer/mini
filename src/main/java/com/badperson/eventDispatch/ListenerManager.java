package com.badperson.eventDispatch;

import java.util.EventObject;
import java.util.List;
import java.util.Map;

import com.badperson.eventDispatch.listener.ExcelEventListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ListenerManager implements IDispatcher{

	private Map<Class<? extends EventObject>, List<ExcelEventListener>> listeners = Maps.newHashMap();

	public void registListener(Class<? extends EventObject> c, ExcelEventListener eventListener) {
		getListenersByClass(c).add(eventListener);
	}

	public void removeListener(Class<? extends EventObject> c, ExcelEventListener eventListener) {
		getListenersByClass(c).remove(eventListener);
	}
	 
	private List<ExcelEventListener> getListenersByClass(Class<? extends EventObject> c){
		if (listeners.get(c) == null) {
			listeners.put(c, Lists.<ExcelEventListener> newArrayList());
		}
		return listeners.get(c);
	}

	@Override
	public void dispatch(EventObject eventObject) {
		for (ExcelEventListener excelEventListener : getListenersByClass(eventObject.getClass())) {
			excelEventListener.onEvent(eventObject);
		}
	}
}
