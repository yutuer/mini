package com.badperson.eventDispatch.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.listener.rowDataEvent.ExcelNavicatMysqlGroupListener_RowDataEvent;
import com.badperson.eventDispatch.listener.rowDataEvent.ExcelNavicatMysqlTunnelListener_RowDataEvent;
import com.badperson.eventDispatch.listener.rowDataEvent.ExcelXShellListener_RowDataEvent;
import com.badperson.eventDispatch.listener.rowDataEvent.GaoyeListener_RowDataEvent;

@Component
public class RowDataEventHandler extends AllEventHandler<RowDataEvent> {

	@Autowired
	private ExcelNavicatMysqlTunnelListener_RowDataEvent excelNavicatMysqlTunnelListener;

	@Autowired
	private ExcelXShellListener_RowDataEvent excelXShellListener;

	@Autowired
	private ExcelNavicatMysqlGroupListener_RowDataEvent excelNavicatMysqlGroupListener;

	@Autowired
	private GaoyeListener_RowDataEvent gaoyeListener;

	@Override
	protected void init() {
		addListener(excelNavicatMysqlTunnelListener);
		addListener(excelXShellListener);
		addListener(excelNavicatMysqlGroupListener);
		addListener(gaoyeListener);
	}

}
