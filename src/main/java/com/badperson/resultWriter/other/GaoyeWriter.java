package com.badperson.resultWriter.other;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.interfaces.IGaoyeWriter;
import com.badperson.module.other.GaoyeModule;
import com.badperson.vo.GaoyeShellParseModel;
import com.badperson.writerParse.ServerExcelWriter;
import com.google.common.collect.Table;

@Component
public class GaoyeWriter implements IGaoyeWriter {

	private static final Logger logger = LoggerFactory.getLogger(GaoyeWriter.class);

	@Autowired
	private GaoyeModule gaoyeModule;

	public GaoyeWriter() {
		super();
	}

	@Override
	public void toGaoye(ServerExcelWriter parse) throws Exception {
		try (Writer fileWriter = new OutputStreamWriter(new FileOutputStream(gaoyeModule.getOutputFilePath(), true),
				Charset.forName(Config.ENCODING_UTF))) {
			Table<Integer, Short, String> tableData = parse.getData();
			for (Integer row : tableData.rowKeySet()) {
				Map<Short, String> map = tableData.row(row);

				GaoyeShellParseModel bean = getGaoyeShellParseModel(map);
				fileWriter.write(bean.getTransferResult());
			}
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
