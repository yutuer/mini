package com.badperson.eventDispatch;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.eventDispatch.eventObject.BeginData;
import com.badperson.eventDispatch.eventObject.BeginEventObject;
import com.badperson.eventDispatch.eventObject.EndData;
import com.badperson.eventDispatch.eventObject.EndEventObject;
import com.badperson.eventDispatch.eventObject.RowData;
import com.badperson.eventDispatch.eventObject.RowDataEventObject;
import com.badperson.eventDispatch.eventObject.StaticBeginEventObject;
import com.badperson.eventDispatch.eventObject.StaticEndEventObject;
import com.badperson.eventDispatch.listener.CreateDirListener;
import com.badperson.eventDispatch.listener.ExcelNavicatMysqlTunnelListener;
import com.badperson.eventDispatch.listener.ExcelXShellListener;
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
	private ListenerManager lm;

	public void parse() {
		lm.dispatch(new StaticBeginEventObject());

		PropertiesReader pr = new PropertiesReader(Config.PORT_FILE);
		for (String excelName : pr.getAllKeys()) {
			lm.dispatch(new BeginEventObject(new BeginData(excelName)));

			ServerExcelWriter writer = FileUtil.getWriters().get(excelName);
			Table<Integer, Short, String> tableData = writer.getData();
			for (Integer row : tableData.rowKeySet()) {
				Map<Short, String> map = tableData.row(row);
				lm.dispatch(new RowDataEventObject(new RowData(excelName, row, map)));
			}

			lm.dispatch(new EndEventObject(new EndData(excelName)));
		}

		lm.dispatch(new StaticEndEventObject());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		FileUtil.init();

		// 注册监听器
		{
			lm.registListener(StaticBeginEventObject.class, createDirListener);
			lm.registListener(StaticBeginEventObject.class, excelNavicatMysqlTunnelListener);
		}

		{
			lm.registListener(BeginEventObject.class, excelXShellListener);

		}

		{
			lm.registListener(RowDataEventObject.class, excelNavicatMysqlTunnelListener);
			lm.registListener(RowDataEventObject.class, excelXShellListener);
		}

		{
			lm.registListener(EndEventObject.class, excelXShellListener);

		}
		{
			lm.registListener(StaticEndEventObject.class, excelNavicatMysqlTunnelListener);
		}
	}
}
