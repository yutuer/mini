package com.badperson.resultWriter.redis;

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
import com.badperson.config.RedisConfig;
import com.badperson.config.ShellConfig;
import com.badperson.interfaces.IShellWriter;
import com.badperson.resultWriter.ExcelWriter;
import com.badperson.vo.ExcelRedisParseModel;
import com.badperson.vo.ExcelXShellParseModel;
import com.badperson.writerParse.ServerExcelWriter;
import com.google.common.collect.Table;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class ExcelRedisShellWriter extends ExcelWriter implements IShellWriter{
	
	private final String fileName;
	
	public static ExcelRedisShellWriter newExcelRedisShellWriter(String fileName) {
		return new ExcelRedisShellWriter(fileName);
	}
	
	private ExcelRedisShellWriter(String fileName) {
		super();
		this.fileName = fileName;
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
			Table<Integer, Short, String> tableData = parse.getData();
			for (Integer row : tableData.rowKeySet()) {
				Map<Short, String> map = tableData.row(row);

				ExcelRedisParseModel bean = getParseBean(map);
				bean.setIndex(index);
				fileWriter.write(bean.getParseResult());
				index++;
			}
			fileWriter.write(ShellConfig.FwdReqCount + "=" + index);
		} finally {
			fileWriter.close();
		}
	}

	public ExcelRedisParseModel getParseBean(Map<Short, String> map) {
		ExcelRedisParseModel bean = new ExcelRedisParseModel();
		bean.setDescription(map.get((short) 0) + "_redis");
		bean.setDestHost(map.get((short) 1));
		bean.setDestHostPort(map.get((short) 2));
		bean.setSourcePort(map.get((short) 3));
		if (map.get((short) 7) != null) {
			bean.setSourcePort(map.get((short) 7));
		}
		return bean;
	}

	@Override
	public String getOutputFilePath() {
		return RedisConfig.OUT_DIR + fileName + RedisConfig.OUT_FILE_SUFFIX;
	}
}
