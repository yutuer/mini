package com.badperson.eventDispatch.listener;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.badperson.config.MysqlConfig;
import com.badperson.config.OtherConfig;
import com.badperson.config.RedisConfig;
import com.badperson.config.ShellConfig;
import com.badperson.eventDispatch.eventObject.StaticBeginEventObject;
import com.badperson.util.FileUtil;

@Component
public class CreateDirListener extends AbstractExcelEventListener {

	public static final Logger logger = LoggerFactory.getLogger(CreateDirListener.class);

	@Override
	public void onEvent(StaticBeginEventObject eventObject) throws Exception {
		String[] paths = { MysqlConfig.OUT_DIR, ShellConfig.OUT_DIR, OtherConfig.OTHER_OUTPUT, RedisConfig.OUT_DIR };
		for (String path : paths) {
			File dir = new File(path);
			FileUtil.deleteFile(dir);
			dir.mkdirs();
		}
	}

}
