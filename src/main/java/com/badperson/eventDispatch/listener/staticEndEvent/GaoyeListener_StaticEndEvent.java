package com.badperson.eventDispatch.listener.staticEndEvent;

import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.StaticEventContext;
import com.badperson.eventDispatch.eventObject.StaticEndEvent;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;

@Component
public class GaoyeListener_StaticEndEvent extends AbstractExcelEventListener<StaticEndEvent>{

	@Override
	public void doHandler(StaticEndEvent event) throws Exception {
		try {
		} finally {
			StaticEventContext.GaoyeListener_fileWriter.close();
			StaticEventContext.GaoyeListener_fileWriter = null;
		}
	}

}
