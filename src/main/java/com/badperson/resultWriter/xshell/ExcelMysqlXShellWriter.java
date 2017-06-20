package com.badperson.resultWriter.xshell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.ShellConfig;
import com.badperson.interfaces.IShellWriter;
import com.badperson.resultWriter.ExcelWriter;
import com.badperson.util.FileUtil;
import com.badperson.vo.ExcelXShellParseModel;
import com.badperson.writerParse.ServerExcelWriter;
import com.google.common.collect.Table;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class ExcelMysqlXShellWriter extends ExcelWriter implements IShellWriter {

	private final String fileName;

	public ExcelMysqlXShellWriter(String fileName) {
		super();
		this.fileName = fileName;
	}

	@Override
	public void toShell(ServerExcelWriter write) throws Exception {
		String outFilePath = getOutputFilePath();
		File file = FileUtil.getFile(outFilePath);

		try (Writer fileWriter = new OutputStreamWriter(new FileOutputStream(file, false),
				Charset.forName(Config.ENCODING))) {
			fileWriter.write(ShellConfig.FirstLine);
			int index = 0;
			Table<Integer, Short, String> tableData = write.getData();
			for (Integer row : tableData.rowKeySet()) {
				Map<Short, String> map = tableData.row(row);

				ExcelXShellParseModel bean = getExcelShellParseBean(map);
				bean.setIndex(index);
				fileWriter.write(bean.getTransferResult());

				index++;
			}
			fileWriter.write(ShellConfig.FwdReqCount + "=" + index);
		}
	}

	public ExcelXShellParseModel getExcelShellParseBean(Map<Short, String> map) {
		ExcelXShellParseModel bean = new ExcelXShellParseModel();
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
