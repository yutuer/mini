package com.badperson.eventDispatch.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.EndEvent;
import com.badperson.eventDispatch.listener.endEvent.ExcelXShellListener_EndEvent;

@Component
public class EndEventHandler extends AllEventHandler<EndEvent> {

	@Autowired
	private ExcelXShellListener_EndEvent ExcelXShellListener;

	@Override
	protected void init() {
		addListener(ExcelXShellListener);
	}

}
