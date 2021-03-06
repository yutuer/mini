package com.badperson.moduleWrite.module.navicat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.MysqlConfig;
import com.badperson.moduleWrite.interfaces.IAction;
import com.badperson.moduleWrite.interfaces.IHead;
import com.badperson.moduleWrite.interfaces.ITail;
import com.badperson.moduleWrite.resultWriter.ExcelWriter;
import com.badperson.moduleWrite.resultWriter.navicat.ExcelNavitorTunnelWriter;
import com.badperson.moduleWrite.resultWriter.navicat.MineExcelNavicatTunnelWriter;
import com.badperson.moduleWrite.writerParse.ServerExcelWriter;
import com.badperson.util.FileUtil;
import com.badperson.util.LoadResource;
import com.badperson.util.PropertiesReader;

@Component
public class NavicatMysqlTunnelWriterModule extends ExcelWriter implements IHead, IAction, ITail {

	@Autowired
	private ExcelNavitorTunnelWriter excelNavitorTunnelWriter;

	@Autowired
	private MineExcelNavicatTunnelWriter mineNavicatTunnelWriter;

	public static final String ReplaceString;
	static {
		StringBuilder sb = new StringBuilder();
		BufferedReader fr = null;
		try (InputStream is = LoadResource.getResourceAsStream(MysqlConfig.TEMPLATE_FILENAME)) {
			fr = new BufferedReader(new InputStreamReader(is, Config.ENCODING_UTF));
			sb.append(fr.readLine());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		ReplaceString = sb.toString();
	}

	public void writeMysqlConnectionFileBegin() throws Exception {
		String outFilePath = getOutputFilePath();
		File file = FileUtil.getFile(outFilePath);

		Writer fileWriter = new OutputStreamWriter(new FileOutputStream(file, true),
				Charset.forName(Config.ENCODING_UTF));
		try {
			fileWriter.write(MysqlConfig.FirstLine);
			fileWriter.write(MysqlConfig.SecondLine);
		} finally {
			fileWriter.close();
		}
	}

	public void writeMysqlConnectionFileEnd() throws Exception {
		String outFilePath = getOutputFilePath();
		Writer fileWriter = new OutputStreamWriter(new FileOutputStream(outFilePath, true),
				Charset.forName(Config.ENCODING_UTF));
		try {
			fileWriter.write(MysqlConfig.LastLine);
		} finally {
			fileWriter.close();
		}
	}

	@Override
	public String getOutputFilePath() {
		return MysqlConfig.OUT_DIR + MysqlConfig.OUT_FILENAME;
	}

	@Override
	public void action() throws Exception {
		PropertiesReader pr = new PropertiesReader(Config.PORT_FILE);
		for (String excelName : pr.getAllKeys()) {
			ServerExcelWriter writer = FileUtil.getWriters().get(excelName);

			// 隧道连接单个
			if ("mine".equals(excelName)) {
				writer.toMysql(mineNavicatTunnelWriter);
			} else {
				writer.toMysql(excelNavitorTunnelWriter);
			}
		}
	}

	@Override
	public void head() throws Exception {
		writeMysqlConnectionFileBegin();
	}

	@Override
	public void tail() throws Exception {
		writeMysqlConnectionFileEnd();
	}

}
