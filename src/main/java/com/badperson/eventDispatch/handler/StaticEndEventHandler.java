package com.badperson.eventDispatch.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.StaticEndEvent;
import com.badperson.eventDispatch.listener.staticEndEvent.ExcelNavicatMysqlGroupListener_StaticEndEvent;
import com.badperson.eventDispatch.listener.staticEndEvent.ExcelNavicatMysqlTunnelListener_StaticEndEvent;
import com.badperson.eventDispatch.listener.staticEndEvent.GaoyeListener_StaticEndEvent;

@Component
public class StaticEndEventHandler extends AllEventHandler<StaticEndEvent> {

	@Autowired
	private ExcelNavicatMysqlTunnelListener_StaticEndEvent excelNavicatMysqlTunnelListener;

	@Autowired
	private ExcelNavicatMysqlGroupListener_StaticEndEvent excelNavicatMysqlGroupListener;

	@Autowired
	private GaoyeListener_StaticEndEvent gaoyeListener;

	@Override
	protected void init() {
		addListener(excelNavicatMysqlTunnelListener);
		addListener(excelNavicatMysqlGroupListener);
		addListener(gaoyeListener);
	}

}
