package com.badperson.eventDispatch.listener.rowDataEvent;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.badperson.config.OtherConfig;
import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.eventObject.eventSource.RowEventSource;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;
import com.badperson.util.PropertiesReader;
import com.badperson.vo.GaoyeShellParseModel;

@Component
public class GaoyeListener_RowDataEvent extends AbstractExcelEventListener<RowDataEvent>{

	private static final PropertiesReader GaoyeConfigPR = new PropertiesReader(OtherConfig.GAOYE_CONF);
	
	@Override
	public void doHandler(RowDataEvent event) throws Exception {
		RowEventSource source = event.getRowEventSource();
		String excelName = source.getExcelName();
		String value = GaoyeConfigPR.getProperties().getProperty(excelName);
		if (value != null) {
			GaoyeShellParseModel bean = getGaoyeShellParseModel(source.getMap());
			event.getContext().getWriter().write(bean.getTransferResult());
		}		
	}
	
	private GaoyeShellParseModel getGaoyeShellParseModel(Map<Short, String> map) {
		GaoyeShellParseModel bean = new GaoyeShellParseModel();
		bean.setDescription(map.get((short) 0));
		bean.setSourcePort(map.get((short) 3));
		if (map.get((short) 5) != null) {
			bean.setGaoye(map.get((short) 5));
		}
		if (map.get((short) 6) != null) {
			bean.setBaseServerId(map.get((short) 6));
		}
		return bean;
	}
}
