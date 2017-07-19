package com.badperson.eventDispatch;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.BeginEventObject;
import com.badperson.eventDispatch.eventObject.EndEventObject;
import com.badperson.eventDispatch.eventObject.IExcelEventObject;
import com.badperson.eventDispatch.eventObject.RowDataEventObject;
import com.badperson.eventDispatch.eventObject.StaticBeginEventObject;
import com.badperson.eventDispatch.eventObject.StaticEndEventObject;
import com.badperson.eventDispatch.listener.ExcelEventListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Component
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
			listeners.put(c, Lists.<ExcelEventListener>newArrayList());
		}
		return listeners.get(c);
	}

	@Override
	public void dispatch(IExcelEventObject eventObject) {
		for (ExcelEventListener excelEventListener : getListenersByClass(eventObject.getClass())) {
			try {
				if (eventObject.getClass() == StaticBeginEventObject.class) {
					excelEventListener.onEvent(StaticBeginEventObject.class.cast(eventObject));
				} else if (eventObject.getClass() == BeginEventObject.class) {
					excelEventListener.onEvent(BeginEventObject.class.cast(eventObject));
				} else if (eventObject.getClass() == EndEventObject.class) {
					excelEventListener.onEvent(EndEventObject.class.cast(eventObject));
				} else if (eventObject.getClass() == RowDataEventObject.class) {
					excelEventListener.onEvent(RowDataEventObject.class.cast(eventObject));
				} else if (eventObject.getClass() == StaticEndEventObject.class) {
					excelEventListener.onEvent(StaticEndEventObject.class.cast(eventObject));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
