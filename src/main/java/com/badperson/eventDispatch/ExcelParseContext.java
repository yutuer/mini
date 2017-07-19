package com.badperson.eventDispatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.eventDispatch.eventObject.BeginEventObject;
import com.badperson.eventDispatch.listener.NavitorTunnelListener;
import com.badperson.moduleWrite.writerParse.ServerExcelWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;

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
			
		}
	}
	
	
	public void parse(){
		PropertiesReader pr = new PropertiesReader(Config.PROP_FILE);
		for (String excelName : pr.getAllKeys()) {
			lm.dispatch(new BeginEventObject<String>(excelName));
			
			
			
			
		}
	}
}
