package com.badperson.eventDispatch.listener.staticEndEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.badperson.config.Config;
import com.badperson.config.GroupJsonConfig;
import com.badperson.eventDispatch.eventObject.StaticEndEvent;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;
import com.badperson.util.FileUtil;
import com.badperson.vo.GroupModel;

@Component
public class ExcelNavicatMysqlGroupListener_StaticEndEvent extends AbstractExcelEventListener<StaticEndEvent> {

	@Autowired
	private GroupModel groupModel;

	@Override
	public void doHandler(StaticEndEvent event) throws Exception {
		String outFilePath = getOutputFilePath();
		File file = FileUtil.getFile(outFilePath);

		String json = JSON.toJSONString(groupModel, true);

		try (Writer fileWriter = new OutputStreamWriter(new FileOutputStream(file, true),
				Charset.forName(Config.ENCODING_UTF))) {
			fileWriter.write(json);
		}
	}

	private String getOutputFilePath() {
		return GroupJsonConfig.OUT_DIR + GroupJsonConfig.OUT_FILENAME;
	}
}
