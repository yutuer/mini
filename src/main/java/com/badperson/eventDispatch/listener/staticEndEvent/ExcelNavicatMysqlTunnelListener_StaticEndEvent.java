package com.badperson.eventDispatch.listener.staticEndEvent;

import org.springframework.stereotype.Component;

import com.badperson.config.MysqlConfig;
import com.badperson.eventDispatch.StaticEventContext;
import com.badperson.eventDispatch.eventObject.StaticEndEvent;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;

@Component
public class ExcelNavicatMysqlTunnelListener_StaticEndEvent extends AbstractExcelEventListener<StaticEndEvent> {

	@Override
	public void doHandler(StaticEndEvent event) throws Exception {
		try {
			StaticEventContext.ExcelNavicatMysqlTunnelListener_fileWriter.write(MysqlConfig.LastLine);
		} finally {
			StaticEventContext.ExcelNavicatMysqlTunnelListener_fileWriter.close();
			StaticEventContext.ExcelNavicatMysqlTunnelListener_fileWriter = null;
		}
	}

}
