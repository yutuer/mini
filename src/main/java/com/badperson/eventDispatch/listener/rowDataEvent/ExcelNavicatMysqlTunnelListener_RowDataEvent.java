package com.badperson.eventDispatch.listener.rowDataEvent;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.eventObject.eventSource.RowEventSource;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;
import com.badperson.vo.ExcelMysqlParseModel_ForNavitorTunnel;

@Component
public class ExcelNavicatMysqlTunnelListener_RowDataEvent extends AbstractExcelEventListener<RowDataEvent>{

	@Override
	public void doHandler(RowDataEvent event) throws Exception {
		RowEventSource source = event.getRowEventSource();

		ExcelMysqlParseModel_ForNavitorTunnel mysqlParseBean = null;
		if (!"mine".equals(source.getExcelName())) {
			mysqlParseBean = getMysqlParseBean(source.getMap());
		} else {
			mysqlParseBean = getMysqlParseBean_Mine(source.getMap());
		}
		event.getContext().getWriter().write(mysqlParseBean.getTransferResult());
	}
	
	private ExcelMysqlParseModel_ForNavitorTunnel getMysqlParseBean(Map<Short, String> map) {
		ExcelMysqlParseModel_ForNavitorTunnel bean = new ExcelMysqlParseModel_ForNavitorTunnel();
		bean.setDescription(map.get((short) 0));
		bean.setSourcePort(map.get((short) 3));
		return bean;
	}

	private ExcelMysqlParseModel_ForNavitorTunnel getMysqlParseBean_Mine(Map<Short, String> map) {
		ExcelMysqlParseModel_ForNavitorTunnel bean = new ExcelMysqlParseModel_ForNavitorTunnel();
		bean.setDescription(map.get((short) 0));
		bean.setHost(map.get((short) 1));
		bean.setSourcePort(map.get((short) 3));
		bean.setUserName(map.get((short) 4));
		bean.setPassword(map.get((short) 5));
		return bean;
	}
}
