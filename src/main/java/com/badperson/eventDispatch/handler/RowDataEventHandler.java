package com.badperson.eventDispatch.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.listener.rowDataEvent.ExcelNavicatMysqlTunnelListener_RowDataEvent;

@Component
public class RowDataEventHandler extends AllEventHandler<RowDataEvent>{

	@Autowired
	private ExcelNavicatMysqlTunnelListener_RowDataEvent excelNavicatMysqlTunnelListener;
	
	@Override
	protected void init() {
		addListener(excelNavicatMysqlTunnelListener);
	}

}
