package com.badperson.resultWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import com.badperson.config.Config;
import com.badperson.config.ShellConfig;
import com.badperson.interfaces.IParseModel;
import com.badperson.interfaces.IShellWriter;
import com.badperson.vo.ExcelShellParseModel;
import com.badperson.writerParse.ServerExcelWriter;
import com.google.common.collect.Table;

public class ExcelShellWriter extends ExcelWriter<String> implements IShellWriter {

	private final String fileName;

	public static ExcelShellWriter newExcelShellTunnelWriter(String fileName) {
		return new ExcelShellWriter(fileName);
	}

	private ExcelShellWriter(String fileName) {
		super();
		this.fileName = fileName;
	}

	public void toShell(ServerExcelWriter parse) throws Exception {
		String outFilePath = getOutputFilePath();
		File file = new File(outFilePath);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();

		Writer fileWriter = new OutputStreamWriter(new FileOutputStream(outFilePath, false), Charset.forName(Config.ENCODING));
		try {
			fileWriter.write(ShellConfig.FirstLine);
			int index = 0;
			Table<Integer, Short, String> tableData = parse.parse();
			for (Integer row : tableData.rowKeySet()) {
				Map<Short, String> map = tableData.row(row);
				IParseModel<String> xShellRecordVO = getParseBean0(map, index);
				fileWriter.write(xShellRecordVO.getParseResult());
				index++;
			}
			fileWriter.write(ShellConfig.FwdReqCount + "=" + index);
		} finally {
			fileWriter.close();
		}
	}
	
	private IParseModel<String> getParseBean0(Map<Short, String> map, int index) {
		ExcelShellParseModel bean = getParseBean(map);
		bean.setIndex(index);
		return bean;
	}

	@Override
	public ExcelShellParseModel getParseBean(Map<Short, String> map) {
		ExcelShellParseModel bean = new ExcelShellParseModel();
		bean.setDescription(map.get((short) 0));
		bean.setDestHost(map.get((short) 1));
		bean.setDestHostPort(map.get((short) 2));
		bean.setSourcePort(map.get((short) 3));
		return bean;
	}

	@Override
	public String getOutputFilePath() {
		return ShellConfig.OUT_DIR + fileName + ShellConfig.OUT_FILE_SUFFIX;
	}

}
