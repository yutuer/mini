package com.badperson.moduleWrite.resultWriter.navicat;

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
import com.badperson.moduleWrite.interfaces.IMysqlWriter;
import com.badperson.moduleWrite.module.navicat.NavicatMysqlTunnelWriterModule;
import com.badperson.vo.ExcelMysqlParseModel_ForNavitorTunnel;
import com.badperson.writerParse.ServerExcelWriter;
import com.google.common.collect.Table;

@Component
public class ExcelNavitorTunnelWriter implements IMysqlWriter {

	private static final Logger logger = LoggerFactory.getLogger(ExcelNavitorTunnelWriter.class);

	@Autowired
	private NavicatMysqlTunnelWriterModule navicatMysqlTunnelWriterModule;

	public ExcelNavitorTunnelWriter() {
		super();
	}

	public void toMysql(ServerExcelWriter parse) throws Exception {
		String mysqlOutFilePath = navicatMysqlTunnelWriterModule.getOutputFilePath();

		try (Writer mysqlFileWriter = new OutputStreamWriter(new FileOutputStream(mysqlOutFilePath, true),
				Charset.forName(Config.ENCODING_UTF))) {
			Table<Integer, Short, String> tableData = parse.getData();
			for (Integer row : tableData.rowKeySet()) {
				Map<Short, String> map = tableData.row(row);
				ExcelMysqlParseModel_ForNavitorTunnel parseModel = getMysqlParseBean(map);
				mysqlFileWriter.write(parseModel.getTransferResult());
			}
		}
	}

	public ExcelMysqlParseModel_ForNavitorTunnel getMysqlParseBean(Map<Short, String> map) {
		ExcelMysqlParseModel_ForNavitorTunnel bean = new ExcelMysqlParseModel_ForNavitorTunnel();
		bean.setDescription(map.get((short) 0));
		bean.setSourcePort(map.get((short) 3));
		return bean;
	}

}
