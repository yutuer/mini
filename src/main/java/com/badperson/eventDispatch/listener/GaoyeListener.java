package com.badperson.eventDispatch.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.OtherConfig;
import com.badperson.eventDispatch.eventObject.RowData;
import com.badperson.eventDispatch.eventObject.RowDataEventObject;
import com.badperson.eventDispatch.eventObject.StaticBeginEventObject;
import com.badperson.eventDispatch.eventObject.StaticEndEventObject;
import com.badperson.util.FileUtil;
import com.badperson.util.PropertiesReader;
import com.badperson.vo.GaoyeShellParseModel;

@Component
public class GaoyeListener extends AbstractExcelEventListener {

	private static final PropertiesReader GaoyeConfigPR = new PropertiesReader(OtherConfig.GAOYE_CONF);

	private Writer fileWriter;

	@Override
	public void onEvent(StaticBeginEventObject eventObject) throws Exception {
		String outFilePath = getOutputFilePath();
		File file = FileUtil.getFile(outFilePath);

		fileWriter = new OutputStreamWriter(new FileOutputStream(file, true), Charset.forName(Config.ENCODING_UTF));
	}

	@Override
	public void onEvent(RowDataEventObject eventObject) throws Exception {
		RowData source = eventObject.getSource();
		String excelName = source.getExcelName();
		String value = GaoyeConfigPR.getProperties().getProperty(excelName);
		if (value != null) {
			GaoyeShellParseModel bean = getGaoyeShellParseModel(source.getMap());
			fileWriter.write(bean.getTransferResult());
		}
	}

	@Override
	public void onEvent(StaticEndEventObject eventObject) throws Exception {
		try {
		} finally {
			fileWriter.close();
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

	private String getOutputFilePath() {
		return OtherConfig.OTHER_OUTPUT + OtherConfig.GAOYE_NEED_FILE;
	}
}
