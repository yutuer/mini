package com.badperson.eventDispatch;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.BeginEvent;
import com.badperson.eventDispatch.eventObject.EndEvent;
import com.badperson.eventDispatch.eventObject.IExcelEvent;
import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.eventDispatch.eventObject.StaticEndEvent;
import com.badperson.eventDispatch.handler.BeginEventHandler;
import com.badperson.eventDispatch.handler.EndEventHandler;
import com.badperson.eventDispatch.handler.IEventHandler;
import com.badperson.eventDispatch.handler.RowDataEventHandler;
import com.badperson.eventDispatch.handler.StaticBeginEventHandler;
import com.badperson.eventDispatch.handler.StaticEndEventHandler;
import com.google.common.collect.Maps;

@Component
public class ListenerManager implements IDispatcher, InitializingBean {

	@Autowired
	private StaticBeginEventHandler staticBeginEventHandler;

	@Autowired
	private BeginEventHandler beginEventHandler;

	@Autowired
	private RowDataEventHandler rowDataEventHandler;

	@Autowired
	private EndEventHandler endEventHandler;

	@Autowired
	private StaticEndEventHandler staticEndEventHandler;

	private Map<Class<? extends IExcelEvent>, IEventHandler<? extends IExcelEvent>> listener = Maps.newHashMap();

	public ListenerManager() {
		super();
	}

	private void initEventHandler() {
		listener.put(StaticBeginEvent.class, staticBeginEventHandler);
		listener.put(BeginEvent.class, beginEventHandler);
		listener.put(RowDataEvent.class, rowDataEventHandler);
		listener.put(EndEvent.class, endEventHandler);
		listener.put(StaticEndEvent.class, staticEndEventHandler);
	}

	@SuppressWarnings("unchecked")
	private <T extends IExcelEvent> IEventHandler<T> getEventHandler(Class<T> c) {
		IEventHandler<? extends IExcelEvent> iEventHandler = listener.get(c);
		return (IEventHandler<T>) iEventHandler;
	}

	@Override
	public void dispatch(IExcelEvent eventObject) {
		@SuppressWarnings("unchecked")
		IEventHandler<IExcelEvent> eventHandler = (IEventHandler<IExcelEvent>) getEventHandler(eventObject.getClass());

		try {
			eventHandler.doHandler(eventObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initEventHandler();
	}
}
