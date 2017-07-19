package com.badperson.eventDispatch.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.MysqlConfig;
import com.badperson.eventDispatch.eventObject.RowData;
import com.badperson.eventDispatch.eventObject.RowDataEventObject;
import com.badperson.eventDispatch.eventObject.StaticBeginEventObject;
import com.badperson.eventDispatch.eventObject.StaticEndEventObject;
import com.badperson.util.FileUtil;
import com.badperson.vo.ExcelMysqlParseModel_ForNavitorTunnel;

@Component
public class ExcelNavicatMysqlTunnelListener extends AbstractExcelEventListener {

	private boolean isInit;
	private Writer fileWriter;

	public void onEvent(StaticBeginEventObject beginEventObject) throws Exception {
		if (!isInit) {
			String outFilePath = getOutputFilePath();
			File file = FileUtil.getFile(outFilePath);

			fileWriter = new OutputStreamWriter(new FileOutputStream(file, true), Charset.forName(Config.ENCODING_UTF));
			fileWriter.write(MysqlConfig.FirstLine);
			fileWriter.write(MysqlConfig.SecondLine);

			isInit = true;
		}
	}

	public void onEvent(StaticEndEventObject endEventObject) throws Exception {
		fileWriter.write(MysqlConfig.LastLine);
		try {
		} finally {
			fileWriter.close();
		}
	}

	public void onEvent(RowDataEventObject rowDataEventObject) throws Exception {
		RowData source = rowDataEventObject.getSource();

		ExcelMysqlParseModel_ForNavitorTunnel mysqlParseBean = null;
		if (!"mine".equals(source.getExcelName())) {
			mysqlParseBean = getMysqlParseBean(source.getMap());
		} else {
			mysqlParseBean = getMysqlParseBean_Mine(source.getMap());
		}
		fileWriter.write(mysqlParseBean.getTransferResult());
	}

	private ExcelMysqlParseModel_ForNavitorTunnel getMysqlParseBean(Map<Short, String> map) {
		ExcelMysqlParseModel_ForNavitorTunnel bean = new ExcelMysqlParseModel_ForNavitorTunnel();
		bean.setDescription(map.get((short) 0));
		bean.setSourcePort(map.get((short) 3));
		return bean;
	}

	private ExcelMysqlParseModel_ForNavitorTunnel getMysqlParseBean_Mine(Map<Short, String> map) {
		ExcelMysqlParseModel_ForNavitorTunnel bean = new ExcelMysqlParseModel_ForNavitorTunnel();
		bean.setDescription(map.get((short) 0));
		bean.setHost(map.get((short) 1));
		bean.setSourcePort(map.get((short) 3));
		bean.setUserName(map.get((short) 4));
		bean.setPassword(map.get((short) 5));
		return bean;
	}

	private String getOutputFilePath() {
		return MysqlConfig.OUT_DIR + MysqlConfig.OUT_FILENAME;
	}
}
