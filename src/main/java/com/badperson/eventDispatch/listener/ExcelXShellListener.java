package com.badperson.eventDispatch.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.ShellConfig;
import com.badperson.eventDispatch.eventObject.BeginEventSource;
import com.badperson.eventDispatch.eventObject.BeginEvent;
import com.badperson.eventDispatch.eventObject.EndEventSource;
import com.badperson.eventDispatch.eventObject.EndEvent;
import com.badperson.eventDispatch.eventObject.RowEventSource;
import com.badperson.eventDispatch.eventObject.RowDataEvent;
import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.util.FileUtil;
import com.badperson.vo.ExcelXShellParseModel;

@Component
public class ExcelXShellListener extends AbstractExcelEventListener {

	private String excelName;
	private Writer fileWriter;
	private int index = 0;

	public void onEvent(BeginEvent eventObject) throws Exception {
		BeginEventSource source = eventObject.getSource();
		excelName = source.getExcelName();
		String outFilePath = getOutputFilePath(excelName);
		File file = FileUtil.getFile(outFilePath);

		fileWriter = new OutputStreamWriter(new FileOutputStream(file, false), Charset.forName(Config.ENCODING));
		fileWriter.write(ShellConfig.FirstLine);
	}

	public void onEvent(EndEvent eventObject) throws Exception {
		EndEventSource source = eventObject.getSource();
		if (excelName == null || !excelName.equals(source.getExcelName())) {
			throw new Exception("begin excelName:" + excelName + " not equal  end excelName: " + source.getExcelName());
		}

		fileWriter.write(ShellConfig.FwdReqCount + "=" + index);
		try {
		} finally {
			fileWriter.close();
		}
	}

	public void onEvent(RowDataEvent rowDataEventObject) throws Exception {
		RowEventSource source = rowDataEventObject.getSource();

		ExcelXShellParseModel bean = getExcelShellParseBean(source.getMap());
		bean.setIndex(index);
		fileWriter.write(bean.getTransferResult());

		index++;
	}

	private ExcelXShellParseModel getExcelShellParseBean(Map<Short, String> map) {
		ExcelXShellParseModel bean = new ExcelXShellParseModel();
		bean.setDescription(map.get((short) 0));
		bean.setDestHost(map.get((short) 1));
		bean.setDestHostPort(map.get((short) 2));
		bean.setSourcePort(map.get((short) 3));
		return bean;
	}

	private String getOutputFilePath(String fileName) {
		return ShellConfig.OUT_DIR + fileName + ShellConfig.OUT_FILE_SUFFIX;
	}

}
