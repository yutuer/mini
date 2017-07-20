package com.badperson.eventDispatch;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.eventDispatch.eventObject.BeginEventSource;
import com.badperson.eventDispatch.eventObject.BeginEvent;
import com.badperson.eventDispatch.eventObject.EndEventSource;
import com.badperson.eventDispatch.eventObject.EndEvent;
import com.badperson.eventDispatch.eventObject.RowEventSource;
import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.eventDispatch.eventObject.StaticEndEvent;
import com.badperson.eventDispatch.listener.CreateDirListener;
import com.badperson.eventDispatch.listener.ExcelNavicatMysqlGroupListener;
import com.badperson.eventDispatch.listener.ExcelNavicatMysqlTunnelListener;
import com.badperson.eventDispatch.listener.ExcelXShellListener;
import com.badperson.eventDispatch.listener.GaoyeListener;
import com.badperson.moduleWrite.writerParse.ServerExcelWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.google.common.collect.Table;

@Component
public class ExcelParseContext implements InitializingBean {

	@Autowired
	private ExcelNavicatMysqlTunnelListener excelNavicatMysqlTunnelListener;

	@Autowired
	private ExcelXShellListener excelXShellListener;

	@Autowired
	private CreateDirListener createDirListener;

	@Autowired
	private ExcelNavicatMysqlGroupListener excelNavicatMysqlGroupListener;

	@Autowired
	private GaoyeListener gaoyeListener;

	@Autowired
	private ListenerManager lm;

	public void parse() {
		lm.dispatch(new StaticBeginEvent());

		PropertiesReader pr = new PropertiesReader(Config.PORT_FILE);
		for (String excelName : pr.getAllKeys()) {
			lm.dispatch(new BeginEvent(new BeginEventSource(excelName)));

			ServerExcelWriter writer = FileUtil.getWriters().get(excelName);
			Table<Integer, Short, String> tableData = writer.getData();
			for (Integer row : tableData.rowKeySet()) {
				Map<Short, String> map = tableData.row(row);
				lm.dispatch(new RowDataEvent(new RowEventSource(excelName, row, map)));
			}

			lm.dispatch(new EndEvent(new EndEventSource(excelName)));
		}

		lm.dispatch(new StaticEndEvent());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		FileUtil.init();

		// 注册监听器
		{
			lm.registListener(StaticBeginEvent.class, createDirListener);
			lm.registListener(StaticBeginEvent.class, excelNavicatMysqlTunnelListener);
			lm.registListener(StaticBeginEvent.class, gaoyeListener);
		}

		{
			lm.registListener(BeginEvent.class, excelXShellListener);
			lm.registListener(BeginEvent.class, excelNavicatMysqlGroupListener);

		}

		{
			lm.registListener(RowDataEvent.class, excelNavicatMysqlTunnelListener);
			lm.registListener(RowDataEvent.class, excelXShellListener);
			lm.registListener(RowDataEvent.class, excelNavicatMysqlGroupListener);
			lm.registListener(RowDataEvent.class, gaoyeListener);
		}

		{
			lm.registListener(EndEvent.class, excelXShellListener);

		}
		{
			lm.registListener(StaticEndEvent.class, excelNavicatMysqlTunnelListener);
			lm.registListener(StaticEndEvent.class, excelNavicatMysqlGroupListener);
			lm.registListener(StaticEndEvent.class, gaoyeListener);
		}
	}
}
