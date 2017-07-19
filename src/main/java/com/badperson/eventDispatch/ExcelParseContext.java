package com.badperson.eventDispatch;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.eventDispatch.eventObject.BeginData;
import com.badperson.eventDispatch.eventObject.BeginEventObject;
import com.badperson.eventDispatch.eventObject.EndData;
import com.badperson.eventDispatch.eventObject.EndEventObject;
import com.badperson.eventDispatch.eventObject.RowData;
import com.badperson.eventDispatch.eventObject.RowDataEventObject;
import com.badperson.eventDispatch.listener.NavitorTunnelListener;
import com.badperson.moduleWrite.writerParse.ServerExcelWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.google.common.collect.Table;

@Component
public class ExcelParseContext {
	
	@Autowired
	private NavitorTunnelListener navitorTunnelListener;
	
	private ListenerManager lm = new ListenerManager();
	
	public ExcelParseContext() {
		super();
		FileUtil.init();
		//注册监听器
		{
			lm.registListener(BeginEventObject.class, navitorTunnelListener);
			
		}
		
		{
			
		}
		
		{
			lm.registListener(EndEventObject.class, navitorTunnelListener);
			
		}
	}
	
	
	public void parse(){
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
	}
}
