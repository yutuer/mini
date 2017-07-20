package com.badperson.eventDispatch;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.BeginEvent;
import com.badperson.eventDispatch.eventObject.EndEvent;
import com.badperson.eventDispatch.eventObject.IExcelEvent;
import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.eventDispatch.eventObject.StaticEndEvent;
import com.badperson.eventDispatch.listener.ExcelEventListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Component
public class ListenerManager implements IDispatcher {

	private Map<Class<? extends IExcelEvent>, List<ExcelEventListener>> listeners = Maps.newHashMap();

	public void registListener(Class<? extends IExcelEvent> c, ExcelEventListener eventListener) {
		getListenersByClass(c).add(eventListener);
	}

	public void removeListener(Class<? extends IExcelEvent> c, ExcelEventListener eventListener) {
		getListenersByClass(c).remove(eventListener);
	}

	private List<ExcelEventListener> getListenersByClass(Class<? extends IExcelEvent> c) {
		if (listeners.get(c) == null) {
			listeners.put(c, Lists.<ExcelEventListener>newArrayList());
		}
		return listeners.get(c);
	}

	@Override
	public void dispatch(IExcelEvent eventObject) {
		for (ExcelEventListener excelEventListener : getListenersByClass(eventObject.getClass())) {
			try {
				if (eventObject.getClass() == StaticBeginEvent.class) {
					excelEventListener.onEvent(StaticBeginEvent.class.cast(eventObject));
				} else if (eventObject.getClass() == BeginEvent.class) {
					excelEventListener.onEvent(BeginEvent.class.cast(eventObject));
				} else if (eventObject.getClass() == EndEvent.class) {
					excelEventListener.onEvent(EndEvent.class.cast(eventObject));
				} else if (eventObject.getClass() == RowDataEvent.class) {
					excelEventListener.onEvent(RowDataEvent.class.cast(eventObject));
				} else if (eventObject.getClass() == StaticEndEvent.class) {
					excelEventListener.onEvent(StaticEndEvent.class.cast(eventObject));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
