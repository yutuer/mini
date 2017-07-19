package com.badperson.eventDispatch;

import java.util.List;
import java.util.Map;

import com.badperson.eventDispatch.eventObject.IExcelEventObject;
import com.badperson.eventDispatch.listener.ExcelEventListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ListenerManager implements IDispatcher {

	private Map<Class<? extends IExcelEventObject>, List<ExcelEventListener>> listeners = Maps.newHashMap();

	public void registListener(Class<? extends IExcelEventObject> c, ExcelEventListener eventListener) {
		getListenersByClass(c).add(eventListener);
	}

	public void removeListener(Class<? extends IExcelEventObject> c, ExcelEventListener eventListener) {
		getListenersByClass(c).remove(eventListener);
	}

	private List<ExcelEventListener> getListenersByClass(Class<? extends IExcelEventObject> c) {
		if (listeners.get(c) == null) {
			listeners.put(c, Lists.<ExcelEventListener> newArrayList());
		}
		return listeners.get(c);
	}

	@Override
	public void dispatch(IExcelEventObject eventObject) {
		for (ExcelEventListener excelEventListener : getListenersByClass(eventObject.getClass())) {
			excelEventListener.onEvent(eventObject);
		}
	}
}
