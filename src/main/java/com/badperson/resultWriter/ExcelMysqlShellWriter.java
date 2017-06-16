package com.badperson.resultWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import com.badperson.config.Config;
import com.badperson.config.ShellConfig;
import com.badperson.interfaces.IModelWriter;
import com.badperson.interfaces.IShellWriter;
import com.badperson.util.GaoyeWriter;
import com.badperson.util.PropertiesReader;
import com.badperson.vo.ExcelShellParseModel;
import com.badperson.writerParse.ServerExcelWriter;
import com.google.common.collect.Table;

public class ExcelMysqlShellWriter extends ExcelWriter<String> implements IShellWriter {

	private final String fileName;
	private IModelWriter otherWriter;
	private static final PropertiesReader GaoyeConfigPR = new PropertiesReader(ShellConfig.GAOYE_CONF);

	public static ExcelMysqlShellWriter newExcelMysqlShellTunnelWriter(String fileName) {
		return new ExcelMysqlShellWriter(fileName);
	}

	private ExcelMysqlShellWriter(String fileName) {
		super();
		this.fileName = fileName;
		String value = GaoyeConfigPR.getProperties().getProperty(fileName);
		if (value != null) {
			this.otherWriter = new GaoyeWriter();
		}
	}

	@Override
	public void toShell(ServerExcelWriter parse) throws Exception {
		String outFilePath = getOutputFilePath();
		File file = new File(outFilePath);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();

		Writer fileWriter = new OutputStreamWriter(new FileOutputStream(outFilePath, false),
				Charset.forName(Config.ENCODING));
		try {
			fileWriter.write(ShellConfig.FirstLine);
			int index = 0;
			Table<Integer, Short, String> tableData = parse.parse();
			for (Integer row : tableData.rowKeySet()) {
				Map<Short, String> map = tableData.row(row);

				ExcelShellParseModel bean = getParseBean(map);
				bean.setIndex(index);
				fileWriter.write(bean.getParseResult());
				if (otherWriter != null) {
					bean.write(otherWriter);
				}
				index++;
			}
			fileWriter.write(ShellConfig.FwdReqCount + "=" + index);
		} finally {
			fileWriter.close();
		}
	}

	public ExcelShellParseModel getParseBean(Map<Short, String> map) {
		ExcelShellParseModel bean = new ExcelShellParseModel();
		bean.setDescription(map.get((short) 0));
		bean.setDestHost(map.get((short) 1));
		bean.setDestHostPort(map.get((short) 2));
		bean.setSourcePort(map.get((short) 3));
		if (map.get((short) 5) != null) {
			bean.setGaoye(map.get((short) 5));
		}
		if (map.get((short) 6) != null) {
			bean.setBaseServerId(map.get((short) 6));
		}
		return bean;
	}

	@Override
	public String getOutputFilePath() {
		return ShellConfig.OUT_DIR + fileName + ShellConfig.OUT_FILE_SUFFIX;
	}

}
