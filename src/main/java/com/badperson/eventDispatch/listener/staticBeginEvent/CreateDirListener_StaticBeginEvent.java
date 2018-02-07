package com.badperson.eventDispatch.listener.staticBeginEvent;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.badperson.config.MysqlConfig;
import com.badperson.config.OtherConfig;
import com.badperson.config.RedisConfig;
import com.badperson.config.ShellConfig;
import com.badperson.eventDispatch.eventObject.StaticBeginEvent;
import com.badperson.eventDispatch.listener.AbstractExcelEventListener;
import com.badperson.util.FileUtil;

@Component
public class CreateDirListener_StaticBeginEvent extends AbstractExcelEventListener<StaticBeginEvent> {

	public static final Logger logger = LoggerFactory.getLogger(CreateDirListener_StaticBeginEvent.class);

	@Override
	public void doHandler(StaticBeginEvent event) throws Exception {
		String[] paths = { MysqlConfig.OUT_DIR, ShellConfig.OUT_DIR, OtherConfig.OTHER_OUTPUT, RedisConfig.OUT_DIR };
		for (String path : paths) {
			File dir = new File(path);
			FileUtil.deleteFile(dir);
			dir.mkdirs();
		}
	}

}
