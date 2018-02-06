package com.badperson.eventDispatch.listener.endEvent;

import org.springframework.stereotype.Component;

import com.badperson.config.ShellConfig;
import com.badperson.eventDispatch.eventObject.EndEvent;
import com.badperson.eventDispatch.eventObject.ExcelEventContext;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;

@Component
public class ExcelXShellListener_EndEvent extends AbstractExcelEventListener<EndEvent> {

	@Override
	public void doHandler(EndEvent event) throws Exception {
		ExcelEventContext context = event.getContext();

		context.getWriter().write(ShellConfig.FwdReqCount + "=" + context.getIndex());
		try {
		} finally {
			context.getWriter().close();
			context.setWriter(null);
		}
	}

}
