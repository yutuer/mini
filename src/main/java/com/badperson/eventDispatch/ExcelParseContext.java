package com.badperson.eventDispatch;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.eventDispatch.eventObject.BeginEvent;
import com.badperson.eventDispatch.eventObject.EndEvent;
import com.badperson.eventDispatch.eventObject.ExcelEventContext;
import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.eventDispatch.eventObject.StaticEndEvent;
import com.badperson.eventDispatch.eventObject.eventSource.RowEventSource;
import com.badperson.moduleWrite.writerParse.ServerExcelWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.google.common.collect.Table;

@Component
public class ExcelParseContext {

	@Autowired
	private ListenerManager lm;

	public void parse() {

		lm.dispatch(new StaticBeginEvent());

		PropertiesReader pr = new PropertiesReader(Config.PORT_FILE);
		for (String excelName : pr.getAllKeys()) {
			ExcelEventContext context = new ExcelEventContext(excelName);

			lm.dispatch(new BeginEvent(excelName, context));

			ServerExcelWriter writer = FileUtil.getWriters().get(excelName);
			Table<Integer, Short, String> tableData = writer.getData();
			for (Integer row : tableData.rowKeySet()) {
				Map<Short, String> map = tableData.row(row);
				lm.dispatch(new RowDataEvent(excelName, context, new RowEventSource(excelName, row, map)));
			}
			lm.dispatch(new EndEvent(excelName, context));
		}

		lm.dispatch(new StaticEndEvent());
	}

}
