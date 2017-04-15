package com.badperson.resultWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badperson.config.Config;
import com.badperson.config.MysqlConfig;
import com.badperson.interfaces.IMysqlWriter;
import com.badperson.interfaces.IParseModel;
import com.badperson.vo.ExcelMysqlParseModel;
import com.badperson.writerParse.ServerExcelWriter;
import com.google.common.collect.Table;

public class ExcelMysqlWriter extends ExcelWriter<String> implements IMysqlWriter {

	private static final Logger logger = LoggerFactory.getLogger(ExcelMysqlWriter.class);
	
	private static ExcelMysqlSpecialWriter _SpecialWriter;

	public static ExcelMysqlWriter newExcelMysqlWriter() {
		return new ExcelMysqlWriter();
	}

	protected ExcelMysqlWriter() {
		super();
	}

	public void toMysql(ServerExcelWriter parse) throws Exception {
		String outFilePath = getOutputFilePath();
		Writer fileWriter = new OutputStreamWriter(new FileOutputStream(outFilePath, true), Charset.forName(Config.ENCODING_UTF));
		try {
			Table<Integer, Short, String> tableData = parse.parse();
			for (Integer row : tableData.rowKeySet()) {
				Map<Short, String> map = tableData.row(row);
				IParseModel<String> parseModel = getParseBean(map, 0);
				fileWriter.write(parseModel.getParseResult());
			}
		} finally {
			fileWriter.close();
		}
	}

	@Override
	public IParseModel<String> getParseBean(Map<Short, String> map, int index) {
		ExcelMysqlParseModel bean = new ExcelMysqlParseModel();
		bean.setDescription(map.get((short) 0));
		bean.setSourcePort(map.get((short) 3));
		return bean;
	}

	@Override
	public String getOutputFilePath() {
		return MysqlConfig.OUT_DIR + MysqlConfig.OUT_FILENAME;
	}

	public static ExcelMysqlSpecialWriter getExcelMysqlSpecialWriter() {
		if (_SpecialWriter == null) {
			_SpecialWriter = new ExcelMysqlSpecialWriter();
		}
		return _SpecialWriter;
	}

	public static class ExcelMysqlSpecialWriter {

		private ExcelMysqlSpecialWriter() {
			super();
		}

		public static final String ReplaceString;

		static {
			StringBuilder sb = new StringBuilder();
			BufferedReader fr = null;
			try {
				ClassLoader cl = Thread.currentThread().getClass().getClassLoader();
				logger.debug("Thread.currentThread().getClass().getClassLoader():" + cl.toString());
				InputStream is = cl.getResourceAsStream(MysqlConfig.TEMPLATE_FILENAME);
				fr = new BufferedReader(new InputStreamReader(is, Config.ENCODING_UTF));
				sb.append(fr.readLine());
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (fr != null) {
					try {
						fr.close();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
			ReplaceString = sb.toString();
		}

		public void writeMysqlFileBegin() throws Exception {
			String outFilePath = ExcelMysqlWriter.newExcelMysqlWriter().getOutputFilePath();
			File file = new File(outFilePath);
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();

			Writer fileWriter = new OutputStreamWriter(new FileOutputStream(outFilePath, true), Charset.forName(Config.ENCODING_UTF));
			try {
				fileWriter.write(MysqlConfig.FirstLine);
				fileWriter.write(MysqlConfig.SecondLine);
			} finally {
				fileWriter.close();
			}
		}

		public void writeMysqlFileEnd() throws Exception {
			String outFilePath = ExcelMysqlWriter.newExcelMysqlWriter().getOutputFilePath();
			Writer fileWriter = new OutputStreamWriter(new FileOutputStream(outFilePath, true), Charset.forName(Config.ENCODING_UTF));
			try {
				fileWriter.write(MysqlConfig.LastLine);
			} finally {
				fileWriter.close();
			}
		}
	}

}
