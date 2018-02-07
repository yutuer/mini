package com.badperson.eventDispatch.listener.rowDataEvent;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.badperson.eventDispatch.eventObject.ExcelEventContext;
import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.eventObject.eventSource.RowEventSource;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;
import com.badperson.vo.ExcelXShellParseModel;

@Component
public class ExcelXShellListener_RowDataEvent extends AbstractExcelEventListener<RowDataEvent> {

	@Override
	public void doHandler(RowDataEvent event) throws Exception {
		RowEventSource source = event.getRowEventSource();
		ExcelEventContext context = event.getContext();

		ExcelXShellParseModel bean = getExcelShellParseBean(source.getMap());
		bean.setIndex(context.getIndex());
		context.getExcelXShellwriter().write(bean.getTransferResult());

		context.setIndex(context.getIndex() + 1);
	}

	private ExcelXShellParseModel getExcelShellParseBean(Map<Short, String> map) {
		ExcelXShellParseModel bean = new ExcelXShellParseModel();
		bean.setDescription(map.get((short) 0));
		bean.setDestHost(map.get((short) 1));
		bean.setDestHostPort(map.get((short) 2));
		bean.setSourcePort(map.get((short) 3));
		return bean;
	}
}
