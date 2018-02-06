package com.badperson.eventDispatch.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.eventDispatch.listener.staticBeginEvent.CreateDirListener_StaticBeginEvent;
import com.badperson.eventDispatch.listener.staticBeginEvent.ExcelNavicatMysqlTunnelListener_StaticBeginEvent;
import com.badperson.eventDispatch.listener.staticBeginEvent.GaoyeListener_StaticBeginEvent;

@Component
public class StaticBeginEventHandler extends AllEventHandler<StaticBeginEvent> {

	@Autowired
	private CreateDirListener_StaticBeginEvent createDirListener;
	
	@Autowired
	private ExcelNavicatMysqlTunnelListener_StaticBeginEvent excelNavicatMysqlTunnelListener;
	
	@Autowired
	private GaoyeListener_StaticBeginEvent gaoyeListener;
	
	@Override
	protected void init() {
		addListener(createDirListener);
		addListener(excelNavicatMysqlTunnelListener);
		addListener(gaoyeListener);
	}

}
