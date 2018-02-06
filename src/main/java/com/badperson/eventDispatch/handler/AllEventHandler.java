package com.badperson.eventDispatch.handler;

import java.util.List;

import com.badperson.eventDispatch.eventObject.IExcelEvent;
import com.badperson.eventDispatch.listener.IExcelEventListener;
import com.google.common.collect.Lists;

public abstract class AllEventHandler<T extends IExcelEvent> extends EventHandler<T> {

	private List<IExcelEventListener<T>> list = Lists.newArrayList();

	@Override
	public void doHandler(T event) throws Exception{
		for (IExcelEventListener<T> iExcelEventListener : list) {
			iExcelEventListener.doHandler(event);
		}
	}

	protected void addListener(IExcelEventListener<T> listener) {
		list.add(listener);
	}
}
