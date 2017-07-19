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
import com.badperson.eventDispatch.eventObject.BeginData;
import com.badperson.eventDispatch.eventObject.BeginEventObject;
import com.badperson.eventDispatch.eventObject.EndData;
import com.badperson.eventDispatch.eventObject.EndEventObject;
import com.badperson.eventDispatch.eventObject.RowData;
import com.badperson.eventDispatch.eventObject.RowDataEventObject;
import com.badperson.eventDispatch.eventObject.StaticBeginEventObject;
import com.badperson.util.FileUtil;
import com.badperson.vo.ExcelXShellParseModel;

@Component
public class ExcelXShellListener extends AbstractExcelEventListener {

	private String excelName;
	private Writer fileWriter;
	private int index = 0;

	public void onEvent(BeginEventObject eventObject) throws Exception {
		BeginData source = eventObject.getSource();
		excelName = source.getExcelName();
		String outFilePath = getOutputFilePath(excelName);
		File file = FileUtil.getFile(outFilePath);

		fileWriter = new OutputStreamWriter(new FileOutputStream(file, false), Charset.forName(Config.ENCODING));
		fileWriter.write(ShellConfig.FirstLine);
	}

	public void onEvent(EndEventObject eventObject) throws Exception {
		EndData source = eventObject.getSource();
		if (excelName == null || !excelName.equals(source.getExcelName())) {
			throw new Exception("begin excelName:" + excelName + " not equal  end excelName: " + source.getExcelName());
		}

		fileWriter.write(ShellConfig.FwdReqCount + "=" + index);
		try {
		} finally {
			fileWriter.close();
		}
	}

	public void onEvent(RowDataEventObject rowDataEventObject) throws Exception {
		RowData source = rowDataEventObject.getSource();

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
