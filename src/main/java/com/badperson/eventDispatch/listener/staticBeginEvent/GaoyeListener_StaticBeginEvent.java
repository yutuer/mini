package com.badperson.eventDispatch.listener.staticBeginEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.springframework.stereotype.Component;

import com.badperson.config.Config;
import com.badperson.config.OtherConfig;
import com.badperson.eventDispatch.StaticEventContext;
import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;
import com.badperson.util.FileUtil;

@Component
public class GaoyeListener_StaticBeginEvent extends AbstractExcelEventListener<StaticBeginEvent> {

	private Writer fileWriter;

	@Override
	public void doHandler(StaticBeginEvent event) throws Exception {
		String outFilePath = getOutputFilePath();
		File file = FileUtil.getFile(outFilePath);

		fileWriter = new OutputStreamWriter(new FileOutputStream(file, true), Charset.forName(Config.ENCODING_UTF));
		StaticEventContext.GaoyeListener_fileWriter = fileWriter;
	}

	private String getOutputFilePath() {
		return OtherConfig.OTHER_OUTPUT + OtherConfig.GAOYE_NEED_FILE;
	}
}
