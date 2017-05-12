package com.badperson;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badperson.config.Config;
import com.badperson.config.ShellConfig;
import com.badperson.resultWriter.ExcelGroupJsonWriter;
import com.badperson.resultWriter.ExcelMysqlWriter;
import com.badperson.resultWriter.ExcelShellWriter;
import com.badperson.resultWriter.MineExcelMysqlWriter;
import com.badperson.util.PropertiesReader;
import com.badperson.writerParse.ServerExcelWriter;

import excelParse.ExcelParse;
import excelParse.IParse;

/**
 * 导出xshell、mysql连接配置
 * 
 * @author Administrator
 *
 */
public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		{
			File dir = new File(ShellConfig.OUT_DIR);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		{
			File dir = new File(ShellConfig.OTHER_OUTPUT);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}

		logger.info("parse begin");
		ExcelMysqlWriter.ExcelMysqlSpecialWriter emw = ExcelMysqlWriter.getExcelMysqlSpecialWriter();
		emw.writeMysqlFileBegin();
		PropertiesReader pr = new PropertiesReader(Config.PROP_FILE);
		for (String excelName : pr.getAllKeys()) {
			String trueExcelFileName = Config.EXCEL_DIR + excelName + ".xlsx";
			IParse ep = new ExcelParse(trueExcelFileName);
			ServerExcelWriter writer = new ServerExcelWriter(ep);

			if ("mine".equals(excelName)) {
				writer.toMysql(MineExcelMysqlWriter.newExcelMysqlWriter());
			} else {
				writer.toShellTunnel(ExcelShellWriter.newExcelShellTunnelWriter(excelName));
				writer.toMysql(ExcelMysqlWriter.newExcelMysqlWriter());
			}
			writer.toGroupJson(ExcelGroupJsonWriter.newExcelGroupJsonWriter(excelName));
		}
		emw.writeMysqlFileEnd();

		ExcelGroupJsonWriter.getGroupModelWriter().writeToFile();

		logger.info("parse end success");
	}
}
