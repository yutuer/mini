package com.badperson.eventDispatch.handler;

import java.util.Map;

import com.badperson.eventDispatch.eventObject.IExcelEvent;
import com.badperson.eventDispatch.listener.IExcelEventListener;
import com.google.common.collect.Maps;

public abstract class ByNameEventHandler<T extends IExcelEvent> extends EventHandler<T> {

	private Map<String, IExcelEventListener<T>> map = Maps.newHashMap();

	@Override
	public void doHandler(T event) throws Exception{
		IExcelEventListener<T> iExcelEventListener = map.get(event.getName());
		if (iExcelEventListener != null) {
			iExcelEventListener.doHandler(event);
		}
	}

	protected void addListener(String name, IExcelEventListener<T> listener) {
		map.put(name, listener);
	}

}
