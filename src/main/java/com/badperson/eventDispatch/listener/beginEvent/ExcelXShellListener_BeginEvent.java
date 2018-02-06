package com.badperson.eventDispatch.listener.beginEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.ShellConfig;
import com.badperson.eventDispatch.eventObject.BeginEvent;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;
import com.badperson.util.FileUtil;

@Component
public class ExcelXShellListener_BeginEvent extends AbstractExcelEventListener<BeginEvent> {

	@Override
	public void doHandler(BeginEvent event) throws Exception {
		String excelName = event.getName();
		String outFilePath = getOutputFilePath(excelName);
		File file = FileUtil.getFile(outFilePath);

		event.getContext().setWriter(
				new OutputStreamWriter(new FileOutputStream(file, false), Charset.forName(Config.ENCODING)));
		event.getContext().getWriter().write(ShellConfig.FirstLine);
	}

	private String getOutputFilePath(String fileName) {
		return ShellConfig.OUT_DIR + fileName + ShellConfig.OUT_FILE_SUFFIX;
	}

}
