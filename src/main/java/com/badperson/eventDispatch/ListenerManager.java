package com.badperson.eventDispatch;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.IExcelEvent;
import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.eventDispatch.handler.IEventHandler;
import com.badperson.eventDispatch.handler.StaticBeginEventHandler;
import com.google.common.collect.Maps;

@Component
public class ListenerManager implements IDispatcher {

	private Map<Class<? extends IExcelEvent>, IEventHandler<? extends IExcelEvent>> listener = Maps.newHashMap();

	public ListenerManager() {
		super();
		initEventHandler();
	}

	private void initEventHandler() {
		listener.put(StaticBeginEvent.class, new StaticBeginEventHandler());
	}

	private <T extends IExcelEvent> IEventHandler<T> getEventHandler(Class<T> c) {
		IEventHandler<? extends IExcelEvent> iEventHandler = listener.get(c);
		return (IEventHandler<T>) iEventHandler;
	}

	@Override
	public void dispatch(IExcelEvent eventObject) {
		IEventHandler<IExcelEvent> eventHandler = (IEventHandler<IExcelEvent>) getEventHandler(eventObject.getClass());
		
		try {
			eventHandler.doHandler(eventObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
