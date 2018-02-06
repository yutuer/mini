package com.badperson.eventDispatch.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.BeginEvent;
import com.badperson.eventDispatch.listener.beginEvent.ExcelNavicatMysqlGroupListener_BeginEvent;
import com.badperson.eventDispatch.listener.beginEvent.ExcelXShellListener_BeginEvent;

@Component
public class BeginEventHandler extends AllEventHandler<BeginEvent> {

	@Autowired
	private ExcelXShellListener_BeginEvent excelXShellListener;

	@Autowired
	private ExcelNavicatMysqlGroupListener_BeginEvent ExcelNavicatMysqlGroupListener;

	@Override
	protected void init() {
		addListener(excelXShellListener);
		addListener(ExcelNavicatMysqlGroupListener);
	}

}
