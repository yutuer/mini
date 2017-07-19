package com.badperson.eventDispatch.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.MysqlConfig;
import com.badperson.eventDispatch.eventObject.BeginEventObject;
import com.badperson.eventDispatch.eventObject.EndEventObject;
import com.badperson.eventDispatch.eventObject.IExcelEventObject;
import com.badperson.util.FileUtil;

@Component
public class NavitorTunnelListener implements ExcelEventListener {

	private boolean isInit;

	@Override
	public void onEvent(IExcelEventObject eventObject) {
		try {
			onEvent(eventObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onEvent(BeginEventObject beginEventObject) throws Exception {
		if (!isInit) {
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

			isInit = true;
		}
	}

	public void onEvent(EndEventObject endEventObject) throws Exception {
		
	}

	private String getOutputFilePath() {
		return MysqlConfig.OUT_DIR + MysqlConfig.OUT_FILENAME;
	}

}
