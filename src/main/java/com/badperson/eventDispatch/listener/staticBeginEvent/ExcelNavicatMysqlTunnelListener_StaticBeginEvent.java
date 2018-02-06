package com.badperson.eventDispatch.listener.staticBeginEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.MysqlConfig;
import com.badperson.eventDispatch.StaticEventContext;
import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;
import com.badperson.util.FileUtil;

@Component
public class ExcelNavicatMysqlTunnelListener_StaticBeginEvent extends AbstractExcelEventListener<StaticBeginEvent> {

	private boolean isInit;

	@Override
	public void doHandler(StaticBeginEvent event) throws Exception {
		if (!isInit) {
			String outFilePath = getOutputFilePath();
			File file = FileUtil.getFile(outFilePath);

			Writer fileWriter = new OutputStreamWriter(new FileOutputStream(file, true),
					Charset.forName(Config.ENCODING_UTF));
			StaticEventContext.ExcelNavicatMysqlTunnelListener_fileWriter = fileWriter;

			fileWriter.write(MysqlConfig.FirstLine);
			fileWriter.write(MysqlConfig.SecondLine);

			isInit = true;
		}
	}

	private String getOutputFilePath() {
		return MysqlConfig.OUT_DIR + MysqlConfig.OUT_FILENAME;
	}
}
